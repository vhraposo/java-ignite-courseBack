package com.rocketseat.courseback.useCase.deleteCourse;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.rocketseat.courseback.repository.CourseRepository;

@Service
public class DeleteCourseUseCase {

    private final CourseRepository repository;

    public DeleteCourseUseCase(final CourseRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id) {
        repository.deleteById(id);
    }

}
