package com.hr_management_system_backend.repository;

import com.hr_management_system_backend.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepartmentRepo extends JpaRepository<Department, Long> {

    Department findDepartmentById(Long id);
}
