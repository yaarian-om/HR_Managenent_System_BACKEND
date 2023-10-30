package com.hr_management_system_backend.dto;

import lombok.Data;

@Data
public class JobVacancyDTO {
    private Long id;
    private String position;
    private Long department_id;
    private String postingDate;
    private String closingDate;


}
