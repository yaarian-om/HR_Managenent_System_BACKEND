package com.hr_management_system_backend.dto;

import lombok.Data;

@Data
public class LeaveRequestDTO {
    private Long id;
    private Long employee_id;
    private String leaveType;
    private String startDate;
    private String endDate;
    private String status;
}
