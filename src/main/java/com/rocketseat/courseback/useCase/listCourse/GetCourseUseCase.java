package com.rocketseat.courseback.useCase.listCourse;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.rocketseat.courseback.model.Course;
import com.rocketseat.courseback.repository.CourseRepository;


@Service
public class GetCourseUseCase {

    private final CourseRepository repository;

    public GetCourseUseCase(final CourseRepository repository) {
        this.repository = repository;
    }

    public Course execute(UUID id) {
        return repository.findById(id).orElse(null);
    }

}
