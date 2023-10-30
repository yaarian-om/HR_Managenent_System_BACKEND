package com.hr_management_system_backend.dto;

import lombok.Data;

@Data
public class DepartmentDTO {
    private Long id;
    private String name;
    private Long department_head_id;
    private String location;
    private String description;


}
