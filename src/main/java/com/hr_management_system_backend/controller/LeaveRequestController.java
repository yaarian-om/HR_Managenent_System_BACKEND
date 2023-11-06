package com.hr_management_system_backend.controller;

import com.hr_management_system_backend.dto.leave_request.LeaveRequestDTO;
import com.hr_management_system_backend.dto.leave_request.LeaveRequestDetailsDTO;
import com.hr_management_system_backend.service.LeaveRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }


// region Leave Request CRUD

    @GetMapping("/get_all_leave_requests")
    public ResponseEntity<List<LeaveRequestDTO>> Get_All_Leave_Requests(){
        List<LeaveRequestDTO> leaveRequestList = leaveRequestService.Get_All_Leave_Requests();
        if(leaveRequestList.size() > 0){
//            return ResponseEntity.ok().body("{" + leaveRequestList + "}");
            return ResponseEntity.ok(leaveRequestList);
        }else{
//            return ResponseEntity.internalServerError().body("{\"message\": \"Data Not Found\"}");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @GetMapping("/get_single_leave_request/{id}")
    public ResponseEntity<LeaveRequestDetailsDTO> Get_Leave_Requests_by_Id(@PathVariable Long id){
        LeaveRequestDetailsDTO request_details = leaveRequestService.Get_Leave_Request_by_ID(id);
        if(request_details != null && request_details.getId() >0){
            return ResponseEntity.ok(request_details);
        }else{
            return ResponseEntity.notFound().build();
        }
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

// endregion Leave Request CRUD

    @GetMapping("/accept/leave_request/{id}")
    public ResponseEntity<Object> Accept_Leave_Request(@PathVariable Long id){
        return null;
    }




}
