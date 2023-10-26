package com.hr_management_system_backend.service;

import com.hr_management_system_backend.dto.EmployeeDTO;
import com.hr_management_system_backend.dto.TokenDTO;
import com.hr_management_system_backend.entity.Department;
import com.hr_management_system_backend.entity.Employee;
import com.hr_management_system_backend.entity.Token;
import com.hr_management_system_backend.repository.IDepartmentRepo;
import com.hr_management_system_backend.repository.IEmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Converter {

    private static IEmployeeRepo employeeRepo;

    private static IDepartmentRepo departmentRepo;

//    public Converter(IEmployeeRepo employeeRepo, IDepartmentRepo departmentRepo) {
//        this.employeeRepo = employeeRepo;
//        this.departmentRepo = departmentRepo;
//    }


    //region Employee Converter
    public static EmployeeDTO Convert(Employee employee){

        System.out.println("Employee Caught = "+employee);
        System.out.println("Employee ID Caught = "+employee.getId());
        EmployeeDTO emp = new EmployeeDTO();
        emp.setId(employee.getId());
        emp.setName(employee.getName());
        emp.setDate_of_birth(employee.getDate_of_birth());
        emp.setAddress(employee.getAddress());
        emp.setEmail(employee.getEmail());
        emp.setPassword(employee.getPassword());
        emp.setPhone(employee.getPhone());
        emp.setDepartment_id(employee.getDepartment().getId());
        System.out.println("Manager  = "+employee.getManager());
        System.out.println("Manager ID  = "+employee.getManager().getId());
        emp.setManager_id(employee.getManager().getId());
        emp.setType(employee.getType().name().toString());
        emp.setId(employee.getId());

        return emp;
    }

    public static Employee Convert(EmployeeDTO employee){

        System.out.println("Caught EMP at the starting of Converter "+employee);
        Employee emp = new Employee();
        emp.setId(employee.getId());
        emp.setName(employee.getName());
        emp.setDate_of_birth(employee.getDate_of_birth());
        emp.setAddress(employee.getAddress());
        emp.setEmail(employee.getEmail());
        emp.setPassword(employee.getPassword());
        emp.setPhone(employee.getPhone());
        System.out.println("Employee Caught in Converter line 57 = "+employee.getId());
        System.out.println("Employee Department ID Caught in Converter line 58 = "+employee.getDepartment_id());
        Department tempDept = departmentRepo.findDepartmentById(employee.getDepartment_id());
        System.out.println("Collected Dept info = "+tempDept);
        emp.setDepartment(tempDept);
        Employee tempManager = employeeRepo.findEmployeeById(employee.getManager_id());
        System.out.println("Collected Manager info = "+tempManager);
        emp.setManager(tempManager);
//        employee.setType(("HR".equals(employee.getType())) ? String.valueOf(Employee.EmployeeType.HR) : String.valueOf(Employee.EmployeeType.Employee));
        emp.setType(Employee.EmployeeType.valueOf(employee.getType()));
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
