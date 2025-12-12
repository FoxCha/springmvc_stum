package com.example.springmvcstum.service;

import com.example.springmvcstum.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    Course save(Course course);

    List<Course> findAll();

    Optional<Course> findById(Long id);

    Course update(Long id, Course updated);

    List<String> availableClassrooms();

    List<String> availableMajors();
}
