package com.hr_management_system_backend.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "PerformanceReview")
public class PerformanceReview {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
//    @JoinColumn(name = "employee_id",nullable = false)
    private Employee employee;

    @Column(nullable = false)
    private String reviewDate;

    @Column(nullable = true)
    private double rating;

    @Column(nullable = true)
    private String comment;


}
