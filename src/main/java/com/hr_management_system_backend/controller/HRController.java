package com.hr_management_system_backend.controller;


import com.hr_management_system_backend.authentication.JwtHelper;
import com.hr_management_system_backend.dto.AttendanceDTO;
import com.hr_management_system_backend.dto.EmployeeDTO;
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


//region Employee
    @GetMapping("/get_all_employee_list")
    public  ResponseEntity<Object> Get_All_Employees(){
//        Show Employees who has active status 1
        return null;
    }

    @GetMapping("/get_single_employee/{id}")
    public ResponseEntity<Object> Get_Employee_by_Id(@PathVariable Long id){
        return null;
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


//endregion Employee


//region Attendance

    @GetMapping("/get_all_attendances")
    public ResponseEntity<Object> Get_All_Attendances(){
        return null;
    }

    @GetMapping("/get_single_attendance/{id}")
    public ResponseEntity<Object> Get_Attendance_by_Id(@PathVariable Long id){
        return null;
    }

    @PostMapping("/create_attendance")
    public ResponseEntity<Object> Create_Attendance(@RequestBody AttendanceDTO attendance){
        return null;
    }



// endregion Attendance
























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
