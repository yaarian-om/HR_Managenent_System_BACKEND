package com.hr_management_system_backend.service;

import com.hr_management_system_backend.dto.leave_request.LeaveRequestDTO;
import com.hr_management_system_backend.dto.leave_request.LeaveRequestDetailsDTO;
import com.hr_management_system_backend.entity.Employee;
import com.hr_management_system_backend.entity.LeaveRequest;
import com.hr_management_system_backend.mapper.Converter;
import com.hr_management_system_backend.repository.IEmployeeRepo;
import com.hr_management_system_backend.repository.ILeaveRequestRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class LeaveRequestService {

    private final ILeaveRequestRepo leaveRequestRepo;
    private final IEmployeeRepo employeeRepo;
    private final HeaderService headerService;



    public LeaveRequestService(ILeaveRequestRepo leaveRequestRepo, IEmployeeRepo employeeRepo, HeaderService headerService) {
        this.leaveRequestRepo = leaveRequestRepo;
        this.employeeRepo = employeeRepo;
        this.headerService = headerService;
    }


    public List<LeaveRequestDTO> Get_All_Leave_Requests(){
        List<LeaveRequest> leaveRequestList = leaveRequestRepo.findAll();
        List<LeaveRequestDTO> leaveRequestListDTO = Converter.Convert(leaveRequestList, LeaveRequestDTO.class);
        for (int i = 0; i < leaveRequestListDTO.size(); i++) {
            LeaveRequest leaveRequest = leaveRequestList.get(i);
            LeaveRequestDTO leaveRequestDTO = leaveRequestListDTO.get(i);

            leaveRequestDTO.setName(leaveRequest.getEmployee().getName());
            leaveRequestDTO.setEmail(leaveRequest.getEmployee().getEmail());

        }
        return leaveRequestListDTO;
    }


    public List<LeaveRequestDTO> Get_All_Leave_Requests_By_Employee(Long id){
        List<LeaveRequest> leaveRequestList = leaveRequestRepo.findLeaveRequestsByEmployee(employeeRepo.findEmployeeById(id));
        List<LeaveRequestDTO> leaveRequestListDTO = Converter.Convert(leaveRequestList, LeaveRequestDTO.class);
        for (int i = 0; i < leaveRequestListDTO.size(); i++) {
            LeaveRequest leaveRequest = leaveRequestList.get(i);
            LeaveRequestDTO leaveRequestDTO = leaveRequestListDTO.get(i);

            leaveRequestDTO.setName(leaveRequest.getEmployee().getName());
            leaveRequestDTO.setEmail(leaveRequest.getEmployee().getEmail());

        }
        return leaveRequestListDTO;
    }


    public LeaveRequestDetailsDTO Get_Leave_Request_by_ID(Long id){

        LeaveRequest leave = leaveRequestRepo.findLeaveRequestById(id);
//        System.out.println("\n Employee = "+leave.getEmployee()+"\n\n");
//        System.out.println("\n Department = "+leave.getEmployee().getDepartment()+"\n\n");
        if (leave != null) {
            LeaveRequestDetailsDTO leave_details = Converter.Convert(leave,LeaveRequestDetailsDTO.class);
            leave_details.setName(leave.getEmployee().getName());
            leave_details.setDepartment_name(leave.getEmployee().getDepartment()!= null ? leave.getEmployee().getDepartment().getName() : null);
            leave_details.setDuration(calculate_Duration(leave.getStartDate(),leave.getEndDate()));
            return leave_details;
        }else{
            return null;
        }
    }

    public int Get_Pending_Leave_Request_Count_By_Employee(Long id){
        return Calculate_Pending_Leave_Request(leaveRequestRepo.findLeaveRequestsByEmployee(employeeRepo.findEmployeeById(id)));
    }



    public boolean Create_Leave_Request(String header, LeaveRequestDTO leaveRequestDTO){

        Employee emp = headerService.Get_User_By_Request_Header(header);
        leaveRequestDTO.setEmail(emp.getEmail());
        LeaveRequest leave = Converter.Convert(leaveRequestDTO, LeaveRequest.class);
        leave.setEmployee(emp);
        var decision = leaveRequestRepo.save(leave);
        return decision.getId() != null;
    }

    private String calculate_Duration(String startDate, String endDate) {
        LocalDate startDateLocal = LocalDate.parse(startDate);
        LocalDate endDateLocal = LocalDate.parse(endDate);
        Period period = Period.between(startDateLocal, endDateLocal);

        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();

        if (years == 0 && months == 0) {
            return days + " Days";
        } else if (years == 0) {
            return months + " Months " + days + " Days";
        } else if (months == 0) {
            return years + " Years " + days + " Days";
        } else {
            return years + " Years " + months + " Months " + days + " Days";
        }
    }

    private int Calculate_Pending_Leave_Request(List<LeaveRequest> leaveRequests){
        int pendingCount = 0;

        for (LeaveRequest leaveRequest : leaveRequests) {
            // Check if the status is "Pending"
            if ("Pending".equals(leaveRequest.getStatus())) {
                pendingCount++;
            }
        }

        return pendingCount;
    }



}
