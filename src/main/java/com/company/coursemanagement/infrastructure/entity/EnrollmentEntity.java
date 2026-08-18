package com.company.coursemanagement.infrastructure.entity;

import com.company.coursemanagement.domain.model.EnrollmentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "inscripcion")
@Getter
@Setter
@ToString
public class EnrollmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Column(name = "enrollment_date", nullable = false)
    private LocalDate enrollmentDate;

    @Column(name = "status", nullable = false)
    @Enumerated(STRING)
    private EnrollmentStatus status;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public EnrollmentEntity() {
    }

    public EnrollmentEntity(Long id, Long studentId, Long courseId, LocalDate enrollmentDate, EnrollmentStatus status) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }
}