package com.hr_management_system_backend.repository;

import com.hr_management_system_backend.entity.Employee;
import com.hr_management_system_backend.entity.PerformanceReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPerformanceReviewRepo extends JpaRepository<PerformanceReview, Long> {
    PerformanceReview findPerformanceReviewById(Long id);
    List<PerformanceReview> findPerformanceReviewsByEmployee(Employee employee);
}
