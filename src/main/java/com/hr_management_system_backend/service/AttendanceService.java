package com.hr_management_system_backend.service;

import com.hr_management_system_backend.dto.attendance.AttendanceDTO;
import com.hr_management_system_backend.dto.attendance.AttendanceDetailsDTO;
import com.hr_management_system_backend.dto.attendance.AttendanceListDTO;
import com.hr_management_system_backend.entity.Attendance;
import com.hr_management_system_backend.mapper.Converter;
import com.hr_management_system_backend.repository.IAttendanceRepo;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AttendanceService {

    private final IAttendanceRepo attendanceRepo;
    private final EmployeeService employeeService;
    private final HeaderService headerService;


    public AttendanceService(IAttendanceRepo attendanceRepo, EmployeeService employeeService, HeaderService headerService) {
        this.attendanceRepo = attendanceRepo;
        this.employeeService = employeeService;
        this.headerService = headerService;
    }


    public List<AttendanceListDTO> Get_All_Attendances(){

        List<Attendance> attendances = attendanceRepo.findAll();
        List<AttendanceListDTO> attendanceListDTO = Converter.Convert(attendances,AttendanceListDTO.class);

        for (int i = 0; i < attendanceListDTO.size(); i++) {
            Attendance attendance = attendances.get(i);
            AttendanceListDTO attendanceDTO = attendanceListDTO.get(i);

            attendanceDTO.setName(attendance.getEmployee().getName());
            attendanceDTO.setEmail(attendance.getEmployee().getEmail());

        }

        return attendanceListDTO;
    }


    public List<AttendanceListDTO> Get_All_Attendances_By_Employee(Long id){



        List<Attendance> attendances = attendanceRepo.findAttendancesByEmployee(employeeService.Get_Employee_By_Id(id));
        List<AttendanceListDTO> attendanceListDTO = Converter.Convert(attendances,AttendanceListDTO.class);

        for (int i = 0; i < attendanceListDTO.size(); i++) {
            Attendance attendance = attendances.get(i);
            AttendanceListDTO attendanceDTO = attendanceListDTO.get(i);

            attendanceDTO.setName(attendance.getEmployee().getName());
            attendanceDTO.setEmail(attendance.getEmployee().getEmail());

        }

        return attendanceListDTO;
    }

    public AttendanceDetailsDTO Get_All_Attendances_By_Id(Long id){

        Attendance attend = attendanceRepo.findAttendanceById(id);
        AttendanceDetailsDTO attend_details = Converter.Convert(attend, AttendanceDetailsDTO.class);
        attend_details.setName(attend.getEmployee().getName());
        attend_details.setEmail(attend.getEmployee().getEmail());
        attend_details.setLate_decision(Late_Decision(attend.getClock_in_time(),"10:00:00.000"));

        return attend_details;
    }





    public boolean Create_Attendance(String header, AttendanceDTO attendance){

        Long emp_id = -1L;
        emp_id = headerService.Get_User_Id_By_Request_Header(header);
        Attendance attendance_entity = Converter.Convert(attendance, Attendance.class);
        attendance_entity.setEmployee(employeeService.Get_Employee_By_Id(emp_id));

        attendanceRepo.save(attendance_entity);

        return true;
    }

    public boolean Create_Attendance(AttendanceDTO attendance){

        Attendance attendance_entity = Converter.Convert(attendance, Attendance.class);
        attendance_entity.setEmployee(employeeService.Get_Employee_By_Id(attendance.getEmployee_id()));

        var decision = attendanceRepo.save(attendance_entity);

        return decision.getId() != null;
    }




    private String Late_Decision(String attend_time, String benchmark_time){
        try {
            // Create SimpleDateFormat objects to parse the time strings
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

            // Parse the time strings into Date objects
            Date attendTime = dateFormat.parse(attend_time);
            Date benchmarkTime = dateFormat.parse(benchmark_time);

            // Compare the two Date objects
            if (attendTime.after(benchmarkTime)) { //  || attendTime.equals(benchmarkTime)
                return "Late";
            } else {
                return "Not Late";
            }
        } catch (ParseException e) {
            // Handle any parsing exceptions if they occur
            return "Invalid time format";
        }
    }




}
