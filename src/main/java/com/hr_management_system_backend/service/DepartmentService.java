package com.hr_management_system_backend.service;

import com.hr_management_system_backend.dto.DepartmentDTO;
import com.hr_management_system_backend.entity.Department;
import com.hr_management_system_backend.entity.Employee;
import com.hr_management_system_backend.mapper.Converter;
import com.hr_management_system_backend.repository.IDepartmentRepo;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private final IDepartmentRepo departmentRepo;
    private final EmployeeService employeeService;


    public DepartmentService(IDepartmentRepo departmentRepo, EmployeeService employeeService) {
        this.departmentRepo = departmentRepo;
        this.employeeService = employeeService;
    }


    public boolean Create_Department(DepartmentDTO department){
        Employee dept_head = employeeService.Get_Employee_By_Id(department.getDepartment_head_id());
        Department temp_dept = Converter.Convert(department, Department.class);
        temp_dept.setDepartment_head(dept_head);
        var decision = departmentRepo.save(temp_dept);
        return decision.getId() != null;
    }


}
