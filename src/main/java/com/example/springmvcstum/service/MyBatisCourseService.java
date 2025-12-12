package com.example.springmvcstum.service;

import com.example.springmvcstum.mapper.CourseMapper;
import com.example.springmvcstum.model.Course;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MyBatisCourseService implements CourseService {

    private final CourseMapper courseMapper;

    public MyBatisCourseService(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Override
    public Course save(Course course) {
        courseMapper.insert(course);
        return course;
    }

    @Override
    public List<Course> findAll() {
        return courseMapper.findAll();
    }

    @Override
    public Optional<Course> findById(Long id) {
        return Optional.ofNullable(courseMapper.findById(id));
    }

    @Override
    public Course update(Long id, Course updated) {
        courseMapper.update(id, updated);
        return updated;
    }

    @Override
    public List<String> availableClassrooms() {
        return Arrays.asList("A101", "B202", "C303");
    }

    @Override
    public List<String> availableMajors() {
        return Arrays.asList("软件工程", "计算机科学", "信息安全");
    }
}
