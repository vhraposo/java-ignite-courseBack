package com.rocketseat.courseback.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.courseback.dto.CourseDTO;
import com.rocketseat.courseback.model.Course;
import com.rocketseat.courseback.useCase.deleteCourse.DeleteCourseUseCase;
import com.rocketseat.courseback.useCase.listCourse.GetCourseUseCase;
import com.rocketseat.courseback.useCase.listCourse.ListCourseUseCase;
import com.rocketseat.courseback.useCase.saveCourse.SaveCourseUseCase;
import com.rocketseat.courseback.useCase.updateActive.UpdateActiveUseCase;
import com.rocketseat.courseback.useCase.updateCourse.UpdateCourseUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final SaveCourseUseCase saveCourseUseCase;
    private final ListCourseUseCase listCourseUseCase;
    private final GetCourseUseCase getCourseUseCase;
    private final UpdateCourseUseCase updateCourseUseCase;
    private final UpdateActiveUseCase updateActiveUseCase;
    private final DeleteCourseUseCase deleteCourseUseCase;

    public CourseController(
            final SaveCourseUseCase saveCourseUseCase,
            final ListCourseUseCase listCourseUseCase,
            final GetCourseUseCase getCourseUseCase,
            final UpdateCourseUseCase updateCourseUseCase,
            final UpdateActiveUseCase updateActiveUseCase,
            final DeleteCourseUseCase deleteCourseUseCase
    ) {
        this.saveCourseUseCase = saveCourseUseCase;
        this.listCourseUseCase = listCourseUseCase;
        this.getCourseUseCase = getCourseUseCase;
        this.updateCourseUseCase = updateCourseUseCase;
        this.updateActiveUseCase = updateActiveUseCase;
        this.deleteCourseUseCase = deleteCourseUseCase;
    }

    @PostMapping
    public ResponseEntity<Course> save(@Valid @RequestBody CourseDTO courseDTO) {
        var result = saveCourseUseCase.execute(courseDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<Course>> list(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "category", required = false) String category
    ) {
        var result = listCourseUseCase.execute(name, category);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> get(@PathVariable String id) {
        var result = getCourseUseCase.execute(UUID.fromString(id));
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable String id, @Valid @RequestBody CourseDTO courseDTO) {
        var result = updateCourseUseCase.execute(UUID.fromString(id), courseDTO);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Course> patch(@PathVariable String id) {
        var result = updateActiveUseCase.execute(UUID.fromString(id));
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> delete(@PathVariable String id) {
        deleteCourseUseCase.execute(UUID.fromString(id));
        return ResponseEntity.ok().build();
    }
}

