package com.hr_management_system_backend.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "LeaveRequest")
public class LeaveRequest {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
//    @JoinColumn(name = "employee_id",nullable = false)
    private Employee employee;
//
//    @ManyToOne
////    @JoinColumn(name = "department_id",nullable = false)
//    private Department department;

    @Column(nullable = false)
    private String leaveType;

    @Column(nullable = false)
    private String startDate;

    @Column(nullable = false)
    private String endDate;

    @Column(nullable = false)
    private String status;

    @Column(nullable = true)
    private String description;




}
