package com.hr_management_system_backend.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne
    private Employee department_head;

    @OneToMany(mappedBy = "department")
    private List<Employee> employeeList;

    @Column(nullable = true)
    private String location;

    @Column(nullable = true)
    private String description;

    @OneToMany(mappedBy = "department")
    private List<JobVacancy> jobVacancyList;




}
