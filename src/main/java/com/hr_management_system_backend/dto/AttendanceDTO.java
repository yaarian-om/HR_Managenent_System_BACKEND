package com.hr_management_system_backend.dto;

import com.hr_management_system_backend.entity.Employee;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AttendanceDTO {

    private Long id;
    private Employee employee;
    private LocalDate date;
    private LocalTime clock_in_time;
    private LocalTime clock_out_time;



}
