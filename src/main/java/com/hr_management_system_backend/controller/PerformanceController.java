package com.hr_management_system_backend.controller;

import com.hr_management_system_backend.dto.performance_review.PerformanceReviewDTO;
import com.hr_management_system_backend.dto.performance_review.PerformanceReviewDetailsDTO;
import com.hr_management_system_backend.dto.performance_review.PerformanceReviewListDTO;
import com.hr_management_system_backend.service.PerformanceReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class PerformanceController {

    private final PerformanceReviewService performanceReviewService;

    public PerformanceController(PerformanceReviewService performanceReviewService) {
        this.performanceReviewService = performanceReviewService;
    }

    // region Performance

    @GetMapping("/get_all_performance")
    public ResponseEntity<List<PerformanceReviewListDTO>> Get_All_Performances(){
        List<PerformanceReviewListDTO> performanceReviewList = performanceReviewService.Get_All_Performance_Review();
        if(!performanceReviewList.isEmpty()){
            return ResponseEntity.ok(performanceReviewList);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @GetMapping("/get_single_performance/{id}")
    public ResponseEntity<PerformanceReviewDetailsDTO> Get_Performance_by_Id(@PathVariable Long id){
        PerformanceReviewDetailsDTO performanceReviewDetails = performanceReviewService.Get_Performance_By_Id(id);
        if(performanceReviewDetails != null){
            return ResponseEntity.ok(performanceReviewDetails);
        }else{
            return ResponseEntity.notFound().build();
        }
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
