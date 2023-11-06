package com.hr_management_system_backend.repository;

import com.hr_management_system_backend.entity.Employee;
import com.hr_management_system_backend.entity.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPayrollRepo extends JpaRepository<Payroll, Long> {

    Payroll findPayrollByEmployee(Employee employee);
    Payroll findPayrollById(Long id);

}
