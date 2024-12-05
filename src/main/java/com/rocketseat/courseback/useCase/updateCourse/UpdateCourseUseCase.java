package com.rocketseat.courseback.useCase.updateCourse;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.rocketseat.courseback.dto.CourseDTO;
import com.rocketseat.courseback.model.Course;
import com.rocketseat.courseback.repository.CourseRepository;
import com.rocketseat.courseback.useCase.listCourse.GetCourseUseCase;

@Service
public class UpdateCourseUseCase {

    private final GetCourseUseCase getCourseUseCase;
    private final CourseRepository repository;

    public UpdateCourseUseCase(
            final GetCourseUseCase getCourseUseCase,
            final CourseRepository repository
    ) {
        this.getCourseUseCase = getCourseUseCase;
        this.repository = repository;
    }

    public Course execute(UUID id, CourseDTO courseDTO) {
        var course = getCourseUseCase.execute(id);
        if (course == null) return null;

        if (courseDTO.name() != null && !courseDTO.name().isBlank()) {
            course.setName(courseDTO.name());
        }
        if (courseDTO.teacher() != null && !courseDTO.teacher().isBlank()) {
            course.setTeacher(courseDTO.teacher());
        }
        if (courseDTO.category() != null && !courseDTO.category().isBlank()) {
            course.setCategory(courseDTO.category());
        }
        return repository.save(course);
    }

}

