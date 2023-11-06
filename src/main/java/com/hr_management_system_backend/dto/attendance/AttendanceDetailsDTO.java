package com.hr_management_system_backend.dto.attendance;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDetailsDTO {

    private Long id;
    private String name;
    private String email;
    private String late_decision;
    private String date;
    private String clock_in_time;
    private String clock_out_time;
}
