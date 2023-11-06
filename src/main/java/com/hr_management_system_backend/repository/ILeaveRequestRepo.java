package com.hr_management_system_backend.repository;

import com.hr_management_system_backend.entity.Employee;
import com.hr_management_system_backend.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILeaveRequestRepo extends JpaRepository<LeaveRequest, Long> {
    LeaveRequest findLeaveRequestById(Long id);

    List<LeaveRequest> findLeaveRequestsByEmployee(Employee employee);
}
