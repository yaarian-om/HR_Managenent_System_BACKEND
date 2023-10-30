package com.hr_management_system_backend.controller;

import com.hr_management_system_backend.dto.AttendanceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class AttendanceController {


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

    @PutMapping("/update/attendance")
    public ResponseEntity<Object> Update_Attendance(@RequestBody AttendanceDTO attendance){
        return null;
    }

    @DeleteMapping("/delete/attendance/{id}")
    public ResponseEntity<Object> Delete_Attendance(@PathVariable Long id){
        return null;
    }



// endregion Attendance
}
