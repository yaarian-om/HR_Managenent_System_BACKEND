package com.hr_management_system_backend.controller;

import com.hr_management_system_backend.dto.attendance.AttendanceDTO;
import com.hr_management_system_backend.dto.attendance.AttendanceDetailsDTO;
import com.hr_management_system_backend.dto.attendance.AttendanceListDTO;
import com.hr_management_system_backend.service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }


    //region Attendance

    @GetMapping("/get_all_attendances")
    public ResponseEntity<List<AttendanceListDTO>> Get_All_Attendances(){
        List<AttendanceListDTO> attendances = attendanceService.Get_All_Attendances();
        if(attendances.size() > 0){
            return ResponseEntity.ok(attendances);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get_single_attendance/{id}")
    public ResponseEntity<AttendanceDetailsDTO> Get_Attendance_by_Id(@PathVariable Long id){
        AttendanceDetailsDTO attendanceDetails =  attendanceService.Get_All_Attendances_By_Id(id);
        if (attendanceDetails != null){
            return ResponseEntity.ok(attendanceDetails);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create_attendance")
    public ResponseEntity<Object> Create_Attendance(@RequestHeader String header, @RequestBody AttendanceDTO attendance){
        boolean decision = attendanceService.Create_Attendance(header, attendance);

        if(decision){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.internalServerError().build();
        }
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
