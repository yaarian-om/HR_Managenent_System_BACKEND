package com.hr_management_system_backend.repository;

import com.hr_management_system_backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepo extends JpaRepository<Employee, Long> {

//region Authorization Code
    Employee findByEmailAndPassword(String email, String password);
    Employee findByEmail(String email);

    Employee findEmployeeById(Long id);

//endregion Authorization Code

}
