package com.hr_management_system_backend.dto.performance_review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceReviewDetailsDTO {
    private Long id;
    private String name;
    private String reviewDate;
    private double rating;
    private String comment;
    private double average_rating;

}
