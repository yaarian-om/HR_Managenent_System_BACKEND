package com.hr_management_system_backend.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetailsDTO {

    private Long id;
    private int pending_leaves_requests;
    private int current_month_absent_count;
    private double salary;

    private double[] yearly_ratings;
    private double average_ratings;

    private String image;
    private String name;
    private String date_of_birth;
    private String address;
    private String phone;
    private String department_name;
    private String manager_name;

}
