package com.hr_management_system_backend.dto.attendance;

import com.hr_management_system_backend.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDTO {

    private Long id;
    private Long employee_id;
    private String date;
    private String clock_in_time;
    private String clock_out_time;



}
