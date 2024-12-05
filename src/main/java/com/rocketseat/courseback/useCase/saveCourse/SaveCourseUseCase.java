package com.rocketseat.courseback.useCase.saveCourse;

import org.springframework.stereotype.Service;

import com.rocketseat.courseback.dto.CourseDTO;
import com.rocketseat.courseback.model.Course;
import com.rocketseat.courseback.repository.CourseRepository;

@Service
public class SaveCourseUseCase {

    private final CourseRepository repository;

    public SaveCourseUseCase(final CourseRepository repository) {
        this.repository = repository;
    }

    public Course execute(CourseDTO courseDTO) {
        var course = courseDTO.toDomain();
        return repository.save(course);
    }
}