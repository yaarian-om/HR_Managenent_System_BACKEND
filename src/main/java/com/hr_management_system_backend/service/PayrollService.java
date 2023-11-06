package com.hr_management_system_backend.service;


import com.hr_management_system_backend.dto.PayrollDTO;
import com.hr_management_system_backend.entity.Payroll;
import com.hr_management_system_backend.mapper.Converter;
import com.hr_management_system_backend.repository.IEmployeeRepo;
import com.hr_management_system_backend.repository.IPayrollRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayrollService {

    private final IPayrollRepo payrollRepo;
    private final IEmployeeRepo employeeRepo;

    public PayrollService(IPayrollRepo payrollRepo, IEmployeeRepo employeeRepo) {
        this.payrollRepo = payrollRepo;
        this.employeeRepo = employeeRepo;
    }

    public List<PayrollDTO> Get_All_Payrolls(){

        List<Payroll> payroll_entities = payrollRepo.findAll();
        List<PayrollDTO> payroll_DTOS = Converter.Convert(payroll_entities, PayrollDTO.class);
        return  payroll_DTOS;
    }

    public PayrollDTO Get_Payroll_By_Employee(Long employee_id){

        Payroll payroll_entities = payrollRepo.findPayrollByEmployee(employeeRepo.findEmployeeById(employee_id));
        PayrollDTO payroll_DTOS = Converter.Convert(payroll_entities, PayrollDTO.class);
        return  payroll_DTOS;
    }



    public boolean Create_Payroll(PayrollDTO payroll){

        Payroll payment = Converter.Convert(payroll, Payroll.class);
        payment.setEmployee(employeeRepo.findEmployeeById(payroll.getEmployee_id()));
        payment.setNet_pay(payroll.getSalary() + payroll.getBonus());
        var decision = payrollRepo.save(payment);
        return decision.getId() > 0;

    }


}
