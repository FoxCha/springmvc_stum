package com.example.springmvcstum.controller;

import com.example.springmvcstum.model.Course;
import com.example.springmvcstum.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/new")
    public String newCourseForm(Model model) {
        model.addAttribute("course", new Course());
        populateOptions(model);
        return "course-form";
    }

    @PostMapping
    public String createCourse(@RequestParam String name,
                               @RequestParam String description,
                               @RequestParam("sessionTime") String sessionTime,
                               @RequestParam Integer capacity,
                               @RequestParam String classroom,
                               @RequestParam(value = "majors", required = false) List<String> majors,
                               @RequestParam String deliveryMethod,
                               Model model) {
        Optional<Long> millis = parseDateToMillis(sessionTime);
        Course course = buildCourse(name, description, millis.orElse(0L), capacity, classroom, majors, deliveryMethod);
        if (!StringUtils.hasText(name) || !millis.isPresent()) {
            model.addAttribute("error", "请填写完整的课程名称和授课时间");
            model.addAttribute("course", course);
            populateOptions(model);
            return "course-form";
        }
        course.setSessionTimeMillis(millis.get());
        courseService.save(course);
        return "redirect:/courses";
    }

    @GetMapping
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "course-list";
    }

    @GetMapping("/{id}")
    public String courseDetail(@PathVariable Long id, Model model) {
        Optional<Course> courseOpt = courseService.findById(id);
        if (!courseOpt.isPresent()) {
            return "redirect:/courses";
        }
        Course course = courseOpt.get();
        model.addAttribute("course", course);
        model.addAttribute("majors", CollectionUtils.isEmpty(course.getMajors()) ? "无" : String.join(", ", course.getMajors()));
        return "course-detail";
    }

    @GetMapping("/{id}/edit")
    public String editCourse(@PathVariable Long id, Model model) {
        Optional<Course> courseOpt = courseService.findById(id);
        if (!courseOpt.isPresent()) {
            return "redirect:/courses";
        }
        model.addAttribute("course", courseOpt.get());
        populateOptions(model);
        return "course-form";
    }

    @PostMapping("/{id}")
    public String updateCourse(@PathVariable Long id,
                               @RequestParam String name,
                               @RequestParam String description,
                               @RequestParam("sessionTime") String sessionTime,
                               @RequestParam Integer capacity,
                               @RequestParam String classroom,
                               @RequestParam(value = "majors", required = false) List<String> majors,
                               @RequestParam String deliveryMethod,
                               Model model) {
        Optional<Long> millis = parseDateToMillis(sessionTime);
        Course course = buildCourse(name, description, millis.orElse(0L), capacity, classroom, majors, deliveryMethod);
        course.setId(id);
        if (!millis.isPresent()) {
            model.addAttribute("error", "授课时间格式不正确");
            model.addAttribute("course", course);
            populateOptions(model);
            return "course-form";
        }
        course.setSessionTimeMillis(millis.get());
        courseService.update(id, course);
        return "redirect:/courses";
    }

    private Optional<Long> parseDateToMillis(String dateStr) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            return Optional.of(date.getTime());
        } catch (ParseException e) {
            return Optional.empty();
        }
    }

    private Course buildCourse(String name, String description, Long millis, Integer capacity, String classroom,
                               List<String> majors, String deliveryMethod) {
        Course course = new Course();
        course.setName(name);
        course.setDescription(description);
        course.setSessionTimeMillis(millis);
        course.setCapacity(capacity);
        course.setClassroom(classroom);
        course.setMajors(majors);
        course.setDeliveryMethod(deliveryMethod);
        return course;
    }

    private void populateOptions(Model model) {
        model.addAttribute("classrooms", courseService.availableClassrooms());
        model.addAttribute("majorOptions", courseService.availableMajors());
    }
}
