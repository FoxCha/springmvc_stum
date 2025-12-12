package com.example.springmvcstum.service;

import com.example.springmvcstum.model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class InMemoryCourseService implements CourseService {

    private final List<Course> courses = Collections.synchronizedList(new ArrayList<>());
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Course save(Course course) {
        course.setId(idGenerator.getAndIncrement());
        courses.add(course);
        return course;
    }

    @Override
    public List<Course> findAll() {
        synchronized (courses) {
            return new ArrayList<>(courses);
        }
    }

    @Override
    public Optional<Course> findById(Long id) {
        synchronized (courses) {
            return courses.stream().filter(c -> c.getId().equals(id)).findFirst();
        }
    }

    @Override
    public Course update(Long id, Course updated) {
        synchronized (courses) {
            findById(id).ifPresent(existing -> {
                courses.remove(existing);
                courses.add(updated);
            });
        }
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
