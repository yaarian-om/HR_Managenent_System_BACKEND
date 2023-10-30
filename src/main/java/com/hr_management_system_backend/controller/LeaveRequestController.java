package com.hr_management_system_backend.controller;

import com.hr_management_system_backend.dto.LeaveRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class LeaveRequestController {



    // region Leave Request

    @GetMapping("/get_all_leave_requests")
    public ResponseEntity<Object> Get_All_Leave_Requests(){
        return null;
    }

    @GetMapping("/get_single_leave_request/{id}")
    public ResponseEntity<Object> Get_Leave_Requests_by_Id(@PathVariable Long id){
        return null;
    }

    @PostMapping("/create/leave_request")
    public ResponseEntity<Object> Create_Leave_Request(@RequestBody LeaveRequestDTO request){
        return null;
    }


    @PutMapping("/update/leave_request")
    public ResponseEntity<Object> Update_Leave_Request(@RequestBody LeaveRequestDTO request){
        return null;
    }

    @DeleteMapping("/delete/leave_request/{id}")
    public ResponseEntity<Object> Delete_Leave_Request(@PathVariable Long id){
        return null;
    }

// endregion Leave Request
}
