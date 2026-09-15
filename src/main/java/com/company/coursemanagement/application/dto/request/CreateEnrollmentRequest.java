package com.company.coursemanagement.application.dto.request;

import jakarta.validation.constraints.NotNull;

public class CreateEnrollmentRequest {

    @NotNull
    private Long studentId;

    @NotNull
    private Long courseId;

    public CreateEnrollmentRequest() {
    }

    public CreateEnrollmentRequest(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "CreateEnrollmentRequest{studentId=" + studentId + ", courseId=" + courseId + "}";
    }
}