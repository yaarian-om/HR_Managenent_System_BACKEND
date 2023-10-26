package com.hr_management_system_backend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDTO {
    private Long id;
    private String name;
    private String date_of_birth;
    private String address;
    private String email;
    private String password;
    private String phone;
    private Long department_id;
    private Long manager_id;
    private String type;
    private String image;
    private int active_status;


}
