package com.hr_management_system_backend.controller;


import com.hr_management_system_backend.authentication.JwtHelper;
import com.hr_management_system_backend.dto.EmployeeDTO;
import com.hr_management_system_backend.dto.login.LoginDTO;
import com.hr_management_system_backend.entity.Employee;
import com.hr_management_system_backend.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hr")
public class HRController {

    private final JwtHelper jwt;
    private final EmployeeService employeeService;

    public HRController(JwtHelper jwt, EmployeeService employeeService) {
        this.jwt = jwt;
        this.employeeService = employeeService;
    }

    @GetMapping("/alive")
    public ResponseEntity<Object> index(){
        return ResponseEntity.ok().body("Relax Developer! Cool Down, Get a Coffee, Your HR is Alive");
    }


    @PostMapping("/login")
    public ResponseEntity<Object> Login_Validation(@RequestBody LoginDTO login){
        String type = "";
        var token = "";
        try{
            System.out.println("Email = "+login.getEmail());

            System.out.println("Password = "+login.getPassword());
            type = employeeService.Validate_Login(login);
            if(type != "" && type != null){
                token = jwt.generateToken(login.getEmail());
                System.out.println("Generated Token = "+token);
                if(token != null){
                    return ResponseEntity.ok().body("{\"token\": \"" + token + "\", \"type\": \"" + type + "\"}");
                }else{
                    return ResponseEntity.internalServerError().body("{\"error\": \"Problem in Generating Token\"}");
                }
            }else{
                return ResponseEntity.internalServerError().body("{\"error\": \"Email and Password did not match\"}");
            }
        }catch (Exception ex){
            System.out.println("Error found in Login_Validation = "+ex.getMessage());
            return ResponseEntity.internalServerError().body("{\"error\": \"Problem in Generating Token or Credential Validation\"}");
        }

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

    @GetMapping("/auth_check")
    public ResponseEntity<Object> Authentication_Check(){
        return ResponseEntity.ok().body("{ \"message\" : \"Working\" }");
    }

    @PostMapping("/abcd")
    public String createEmployee(@RequestBody EmployeeDTO employee){
        System.out.println("Emp = "+employee);
        return "Employee Caught";
    }



}
