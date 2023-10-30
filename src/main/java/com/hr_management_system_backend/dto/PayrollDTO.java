package com.hr_management_system_backend.dto;

import lombok.Data;

@Data
public class PayrollDTO {
    private Long id;
    private Long employee_id;
    private double salary;
    private double bonus;
    private double net_pay;

}
