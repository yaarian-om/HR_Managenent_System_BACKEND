package com.hr_management_system_backend.controller;

import com.hr_management_system_backend.dto.employee.EmployeeDTO;
import com.hr_management_system_backend.dto.employee.EmployeeDetailsDTO;
import com.hr_management_system_backend.dto.employee.EmployeeListDTO;
import com.hr_management_system_backend.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    //region Employee
    @GetMapping("/get_all_employee_list")
    public ResponseEntity<List<EmployeeListDTO>> Get_All_Employees(){
//        Show Employees who has active status 1
        List<EmployeeListDTO> employeeListDTOS = employeeService.Get_All_Employees();

        if(employeeListDTOS.size() > 0){
            return ResponseEntity.ok(employeeListDTOS);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get_single_employee/{id}")
    public ResponseEntity<EmployeeDetailsDTO> Get_Employee_by_Id(@PathVariable Long id){
        EmployeeDetailsDTO emp_details = employeeService.Get_Employee_Details_By_Id(id);
        if(emp_details.getId() > 0){
            return ResponseEntity.ok(emp_details);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get_ex_employee_list")
    public ResponseEntity<Object> Get_All_Ex_Employees(){
//        Show Employees who has active status 0
        return null;
    }

    @PostMapping("/create/employee")
    public ResponseEntity<Object> Create_Employee(@RequestBody EmployeeDTO employee){
        boolean decision = employeeService.Create_Employee(employee);
        if (decision){
            return ResponseEntity.ok().body("{ \"message\" : \"Created\" }");
        }else{
            return ResponseEntity.internalServerError().body("{ \"message\" : \"Not Created\" }");
        }
    }

    @PutMapping("/update/employee")
    public ResponseEntity<Object> Update_Employee_Info(@RequestBody EmployeeDTO employee){
        return null;
    }

    @DeleteMapping("/delete/employee/{id}")
    public ResponseEntity<Object> Delete_Employee(@PathVariable Long id){
//        Change Active Status to 0;
        return null;
    }


//endregion Employees

    @GetMapping("/get_hr_dashboard")
    public ResponseEntity<Object> Show_HR_Dashboard(@RequestHeader("Authorization") String header){
        return null;
    }

    @GetMapping("/get_employee_dashboard")
    public ResponseEntity<Object> Show_Employee_Dashboard(@RequestHeader("Authorization") String header){
        return null;
    }

    @GetMapping("/get_my_profile")
    public ResponseEntity<Object> Show_My_Profile(@RequestHeader("Authorization") String header){
        return null;
    }




}
