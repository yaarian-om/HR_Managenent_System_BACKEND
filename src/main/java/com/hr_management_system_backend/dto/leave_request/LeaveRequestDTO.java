package com.hr_management_system_backend.dto.leave_request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequestDTO {
    private Long id;
//    private Long employee_id;
    private String name;
    private String email;
    private String leaveType;
    private String startDate;
    private String endDate;
    private String status;
    private String description;
}
