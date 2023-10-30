package com.hr_management_system_backend.dto;

import lombok.Data;

@Data
public class BenefitDTO {

    private Long id;
    private Long employee_id;
    private String benefitType;
    private String benefitStartDate;
    private String benefitEndDate;

}
