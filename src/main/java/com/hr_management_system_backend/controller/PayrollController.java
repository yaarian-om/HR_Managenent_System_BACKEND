package com.hr_management_system_backend.controller;

import com.hr_management_system_backend.dto.PayrollDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class PayrollController {

    // region Payroll

    @GetMapping("/get_all_payrolls")
    public ResponseEntity<Object> Get_All_Payrolls(){
        return null;
    }

    @GetMapping("/get_single_payroll/{id}")
    public ResponseEntity<Object> Get_Payroll_by_Id(@PathVariable Long id){
        return null;
    }

    @PostMapping("/create/payroll")
    public ResponseEntity<Object> Create_Payroll(@RequestBody PayrollDTO payroll){
        return null;
    }


    @PutMapping("/update/payroll")
    public ResponseEntity<Object> Update_Payroll(@RequestBody PayrollDTO payroll){
        return null;
    }

    @DeleteMapping("/delete/payroll/{id}")
    public ResponseEntity<Object> Delete_Payroll(@PathVariable Long id){
        return null;
    }

// endregion Payroll
}
