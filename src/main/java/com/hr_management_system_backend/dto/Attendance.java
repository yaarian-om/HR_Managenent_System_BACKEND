package com.hr_management_system_backend.dto;

import com.hr_management_system_backend.entity.Employee;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalTime;

public class Attendance {

    private Long id;
    private Employee employee;
    private LocalDate date;
    private LocalTime clock_in_time;
    private LocalTime clock_out_time;


}
