package com.example.springmvcstum.service;

import com.example.springmvcstum.model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class InMemoryCourseService implements CourseService {
    private final ConcurrentHashMap<Long, Course> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    private final List<String> classrooms = Arrays.asList("11A101", "17B202", "2303");
    private final List<String> majors = Arrays.asList("计科", "软工", "平面设计");

    @Override
    public Course save(Course course) {
        long id = idGenerator.getAndIncrement();
        course.setId(id);
        store.put(id, course);
        return course;
    }

    @Override
    public List<Course> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Course> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Course update(Long id, Course updated) {
        updated.setId(id);
        store.put(id, updated);
        return updated;
    }

    @Override
    public List<String> availableClassrooms() {
        return Collections.unmodifiableList(classrooms);
    }

    @Override
    public List<String> availableMajors() {
        return Collections.unmodifiableList(majors);
    }
}
