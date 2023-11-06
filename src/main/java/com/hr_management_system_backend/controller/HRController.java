package com.hr_management_system_backend.controller;


import com.hr_management_system_backend.dto.employee.EmployeeDTO;
import com.hr_management_system_backend.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hr")
public class HRController {

    private final EmployeeService employeeService;


    public HRController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/alive")
    public ResponseEntity<Object> index(){
        return ResponseEntity.ok().body("Relax Developer! Cool Down, Get a Coffee, Your HR is Alive");
    }




    @GetMapping("/auth_check")
    public ResponseEntity<Object> Authentication_Check(){
        return ResponseEntity.ok().body("{ \"message\" : \"Working\" }");
    }

    @PostMapping("/abcd")
    public String createEmployee(@RequestHeader("Authorization") String header, @RequestBody EmployeeDTO employee){

        System.out.println("Header = "+header);
        System.out.println("Emp = "+employee);
        return "Employee Caught";
    }







}
