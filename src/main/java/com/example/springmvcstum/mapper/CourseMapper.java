package com.example.springmvcstum.mapper;

import com.example.springmvcstum.model.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {
    int insert(Course course);

    List<Course> findAll();

    Course findById(@Param("id") Long id);

    int update(@Param("id") Long id, @Param("course") Course course);
}
