package com.hr_management_system_backend.dto;

import lombok.Data;

@Data
public class PerformanceReviewDTO {
    private Long id;
    private Long employee_id;
    private String reviewDate;
    private int rating;
    private String comment;

}
