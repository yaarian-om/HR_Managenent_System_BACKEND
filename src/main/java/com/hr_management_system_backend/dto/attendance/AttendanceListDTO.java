package com.hr_management_system_backend.dto.attendance;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceListDTO {

    private Long id;
    private String name;
    private String email;
    private String date;
    private String clock_in_time;
    private String clock_out_time;

}
