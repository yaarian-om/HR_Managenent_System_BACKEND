package com.hr_management_system_backend.dto.employee;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeListDTO {

    private Long id;
    private String name;
    private String email;
    private String department_name;
    private String phone;
    private String address;
    private String manager_name;

}
