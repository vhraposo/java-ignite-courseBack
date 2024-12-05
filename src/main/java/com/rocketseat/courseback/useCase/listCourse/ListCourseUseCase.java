package com.rocketseat.courseback.useCase.listCourse;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rocketseat.courseback.model.Course;
import com.rocketseat.courseback.repository.CourseRepository;

@Service
public class ListCourseUseCase {

    private final CourseRepository repository;

    public ListCourseUseCase(final CourseRepository repository) {
        this.repository = repository;
    }

    public List<Course> execute(String name, String category) {
        return repository.listByNameOrCategory(name, category);
    }

}

