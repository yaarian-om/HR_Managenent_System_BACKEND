package com.hr_management_system_backend.dto.performance_review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceReviewDTO {
    private Long id;
    private Long employee_id;
    private String reviewDate;
    private double rating;
    private String comment;

}
