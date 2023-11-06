package com.hr_management_system_backend.dto.leave_request;

import lombok.Data;

@Data
public class LeaveRequestDetailsDTO {

    private Long id;
    private String name;
    private String department_name;
    private String startDate;
    private String endDate;
    private String status;
    private String description;
    private String duration;

}
