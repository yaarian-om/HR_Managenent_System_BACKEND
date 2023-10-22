package com.hr_management_system_backend.service;

import com.hr_management_system_backend.dto.EmployeeDTO;
import com.hr_management_system_backend.dto.TokenDTO;
import com.hr_management_system_backend.entity.Employee;
import com.hr_management_system_backend.entity.Token;
import com.hr_management_system_backend.repository.IDepartmentRepo;
import com.hr_management_system_backend.repository.IEmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Converter {

    @Autowired
    private static IEmployeeRepo employeeRepo;
    @Autowired
    private static IDepartmentRepo departmentRepo;



//region Employee Converter
    public static EmployeeDTO Convert(Employee employee){

        EmployeeDTO emp = new EmployeeDTO();
        emp.setId(employee.getId());
        emp.setName(employee.getName());
        emp.setDate_of_birth(employee.getDate_of_birth());
        emp.setAddress(employee.getAddress());
        emp.setEmail(employee.getEmail());
        emp.setPassword(employee.getPassword());
        emp.setPhone(employee.getPhone());
        emp.setDepartment_id(employee.getDepartment().getId());
        emp.setManager_id(employee.getManager().getId());
        emp.setType(employee.getType().name().toString());
        emp.setId(employee.getId());

        return emp;
    }

    public static Employee Convert(EmployeeDTO employee){

        Employee emp = new Employee();
        emp.setId(employee.getId());
        emp.setName(employee.getName());
        emp.setDate_of_birth(employee.getDate_of_birth());
        emp.setAddress(employee.getAddress());
        emp.setEmail(employee.getEmail());
        emp.setPassword(employee.getPassword());
        emp.setPhone(employee.getPhone());
        emp.setDepartment(departmentRepo.getReferenceById(employee.getDepartment_id()));
        emp.setManager(employeeRepo.getReferenceById(employee.getManager_id()));
        employee.setType(("HR".equals(employee.getType())) ? String.valueOf(Employee.EmployeeType.HR) : String.valueOf(Employee.EmployeeType.Employee));
        emp.setImage(employee.getImage());
        emp.setActive_status(employee.getActive_status());


        return emp;
    }
//endregion Employee Converter

//region Token Converter
    public static TokenDTO Convert(Token token){
            TokenDTO tk = new TokenDTO();
            tk.setId(token.getId());
            tk.setToken(token.getToken());
            tk.setExpiredAt(token.getExpiredAt());

            return tk;
    }

    public static Token Convert(TokenDTO token){
        Token tk = new Token();
        tk.setId(token.getId());
        tk.setToken(token.getToken());
        tk.setExpiredAt(token.getExpiredAt());

        return tk;
    }

//endregion Token Converter


}
