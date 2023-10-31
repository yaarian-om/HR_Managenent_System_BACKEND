package com.hr_management_system_backend.controller;

import com.hr_management_system_backend.dto.PerformanceReviewDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PerformanceController {

    // region Performance

    @GetMapping("/get_all_performance")
    public ResponseEntity<Object> Get_All_Performances(){
        return null;
    }

    @GetMapping("/get_single_performance/{id}")
    public ResponseEntity<Object> Get_Performance_by_Id(@PathVariable Long id){
        return null;
    }

    @PostMapping("/create/performance")
    public ResponseEntity<Object> Create_Performance(@RequestBody PerformanceReviewDTO performance){
        return null;
    }


    @PutMapping("/update/performance")
    public ResponseEntity<Object> Update_Performance(@RequestBody PerformanceReviewDTO performance){
        return null;
    }

    @DeleteMapping("/delete/performance/{id}")
    public ResponseEntity<Object> Delete_Performance(@PathVariable Long id){
        return null;
    }

// endregion Performance

}
