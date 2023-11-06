package com.hr_management_system_backend.repository;

import com.hr_management_system_backend.entity.Attendance;
import com.hr_management_system_backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAttendanceRepo extends JpaRepository<Attendance, Long> {

    Attendance findAttendanceById(Long id);
    List<Attendance> findAttendancesByEmployee(Employee employee);


}
