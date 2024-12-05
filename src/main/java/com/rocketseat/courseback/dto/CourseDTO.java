package com.rocketseat.courseback.dto;

import com.rocketseat.courseback.model.Course;
import com.rocketseat.courseback.model.Status;

public record CourseDTO(String name, String teacher, String category) {

    public Course toDomain() {
        return Course.builder()
                .name(name)
                .teacher(teacher)
                .category(category)
                .active(Status.INACTIVE)
                .build();
    }

}
