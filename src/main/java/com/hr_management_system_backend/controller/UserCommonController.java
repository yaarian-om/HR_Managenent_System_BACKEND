package com.hr_management_system_backend.controller;

import com.hr_management_system_backend.authentication.JwtHelper;
import com.hr_management_system_backend.dto.login.LoginDTO;
import com.hr_management_system_backend.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class UserCommonController {

    private final JwtHelper jwt;
    private final EmployeeService employeeService;

    public UserCommonController(JwtHelper jwt, EmployeeService employeeService) {
        this.jwt = jwt;
        this.employeeService = employeeService;
    }


    @PostMapping("/login")
    public ResponseEntity<Object> Login_Validation(@RequestBody LoginDTO login){
        String type = "";
        var token = "";
        try{
            System.out.println("Email = "+login.getEmail());

            System.out.println("Password = "+login.getPassword());
            type = employeeService.Validate_Login(login);
//            type!="" && type !=nul
            if(!Objects.equals(type, "") && type != null){
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


    @PostMapping("/forget_password/request_for_pin")
    public ResponseEntity<Object> Request_for_Pin(@RequestBody String email){

//        First check if there is any user according to that provided email
//        Use SMTP to send a mail to the provided email address
//        Store the Pin in a Pin-Table having one-to-one relationship with Employee
//        Generate a New Token and Send it as a response to the User Frontend
//        Now generate a JWT token and send it as a response

        return null;
    }

    @PostMapping("forget_password/verify_pin")
    public ResponseEntity<Object> Verify_Pin(@RequestHeader("Authorization") String header, @RequestBody String pin){

//        Send to the Service where first grab the email from the JWT, then find the pin against him.
//        If the pin got matched, then return a message "ok". Else return not matched.
//        Also change or update the pin in the database so that any-other password changes can not be done through it

        return null;
    }

    @PostMapping("forget_password/new_password")
    public ResponseEntity<Object> Set_New_Password(@RequestHeader("Authorization") String header, @RequestBody LoginDTO login){


        return null;
    }






























}
