package com.company.coursemanagement.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "curso")
@Getter
@Setter
@ToString
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Positive
    @Min(value = 15, message = "Minima capacidad del curso debe ser al menos 15")
    @Max(value = 30, message = "Maxima capacidad del curso debe ser como maximo 30")
    @Column(name = "max_capacity", nullable = false)
    private Integer maxCapacity;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public CourseEntity() {
    }

    public CourseEntity(Long id, String code, String name, String description, Integer maxCapacity) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.maxCapacity = maxCapacity;
    }
}