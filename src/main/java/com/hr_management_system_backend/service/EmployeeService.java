package com.hr_management_system_backend.service;

import com.hr_management_system_backend.dto.EmployeeDTO;
import com.hr_management_system_backend.dto.login.LoginDTO;
import com.hr_management_system_backend.entity.Employee;
import com.hr_management_system_backend.repository.IEmployeeRepo;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService {

    private final IEmployeeRepo employeeRepo;

    private final Converter converter;

    public EmployeeService(IEmployeeRepo employeeRepo, Converter converter) {
        this.employeeRepo = employeeRepo;
        this.converter = converter;
    }

    public String Validate_Login(LoginDTO login){
//        String type = "";
        Employee valid_employee = employeeRepo.findByEmailAndPassword(login.getEmail(), login.getPassword());
        if(valid_employee.getName() != null && !valid_employee.getName().isEmpty()){
            return valid_employee.getType().toString();
        }else{
            return null;
        }
//        return type;
    }

    public boolean Create_Employee(EmployeeDTO employee){
        var decision = employeeRepo.save(converter.Convert(employee));
        return true;
    }

    public EmployeeDTO Get_Employee_By_Email(String email){
        return converter.Convert(employeeRepo.findByEmail(email));
    }

    public Employee Get_Employee_By_Email_for_Authentication(String email){
        return employeeRepo.findByEmail(email);
    }





}
