package com.hr_management_system_backend.controller;

import com.hr_management_system_backend.dto.BenefitDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class BenefitController {

    // region Benefit

    @GetMapping("/get_all_benefits")
    public ResponseEntity<Object> Get_All_Benefits(){
        return null;
    }

    @GetMapping("/get_single_benefit/{id}")
    public ResponseEntity<Object> Get_Benefit_by_Id(@PathVariable Long id){
        return null;
    }

    @PostMapping("/create/benefit")
    public ResponseEntity<Object> Create_Benefit(@RequestBody BenefitDTO benefit){
        return null;
    }


    @PutMapping("/update/benefit")
    public ResponseEntity<Object> Update_Benefit(@RequestBody BenefitDTO benefit){
        return null;
    }

    @DeleteMapping("/delete/benefit/{id}")
    public ResponseEntity<Object> Delete_Benefit(@PathVariable Long id){
        return null;
    }

// endregion Benefit
}
