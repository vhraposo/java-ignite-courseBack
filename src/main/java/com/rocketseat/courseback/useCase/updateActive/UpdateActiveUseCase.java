package com.rocketseat.courseback.useCase.updateActive;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.rocketseat.courseback.model.Course;
import com.rocketseat.courseback.repository.CourseRepository;
import com.rocketseat.courseback.useCase.listCourse.GetCourseUseCase;

@Service
public class UpdateActiveUseCase {

    private final GetCourseUseCase getCourseUseCase;
    private final CourseRepository repository;

    public UpdateActiveUseCase(
            final GetCourseUseCase getCourseUseCase,
            final CourseRepository repository
    ) {
        this.getCourseUseCase = getCourseUseCase;
        this.repository = repository;
    }

    public Course execute(UUID id) {
        var course = getCourseUseCase.execute(id);
        if (course == null) return null;

        course.toggleActive();
        return repository.save(course);
    }

}
