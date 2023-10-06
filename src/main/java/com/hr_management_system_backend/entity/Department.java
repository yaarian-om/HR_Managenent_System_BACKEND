package com.hr_management_system_backend.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

//    @Column(nullable = true,unique = true)
    @ManyToOne
    @JoinColumn(name = "department_head_id", nullable = true)
    private Employee employee;

    @Column(nullable = true)
    private String location;

    @Column(nullable = true)
    private String description;




}
