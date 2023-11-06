package com.hr_management_system_backend.service;

import com.hr_management_system_backend.dto.employee.EmployeeDTO;
import com.hr_management_system_backend.dto.employee.EmployeeDetailsDTO;
import com.hr_management_system_backend.dto.employee.EmployeeListDTO;
import com.hr_management_system_backend.dto.login.LoginDTO;
import com.hr_management_system_backend.entity.Employee;
import com.hr_management_system_backend.mapper.Converter;
import com.hr_management_system_backend.repository.IEmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeService {

    private final IEmployeeRepo employeeRepo;
    private final LeaveRequestService leaveRequestService;
    private final AttendanceService attendanceService;
    private final PerformanceReviewService performanceReviewService;
    private final PayrollService payrollService;

    private final Converter converter;

    public EmployeeService(IEmployeeRepo employeeRepo, LeaveRequestService leaveRequestService, AttendanceService attendanceService, PerformanceReviewService performanceReviewService, PayrollService payrollService, Converter converter) {
        this.employeeRepo = employeeRepo;
        this.leaveRequestService = leaveRequestService;
        this.attendanceService = attendanceService;
        this.performanceReviewService = performanceReviewService;
        this.payrollService = payrollService;
        this.converter = converter;
    }

    public String Validate_Login(LoginDTO login){
//        String type = "";
        Employee valid_employee = employeeRepo.findByEmailAndPassword(login.getEmail(), login.getPassword());
        if(valid_employee.getName() != null && !valid_employee.getName().isEmpty()){
            return valid_employee.getType().toString();
        }else{
            return null;
        }
//        return type;
    }






    public EmployeeDTO Get_Employee_By_Email(String email){
        return Converter.Convert(employeeRepo.findByEmail(email), EmployeeDTO.class);
    }

    public Employee Get_Employee_By_Email_for_Authentication(String email){
        return employeeRepo.findByEmail(email);
    }

    public Employee Get_Employee_By_Id(Long id){
        return employeeRepo.findEmployeeById(id);
    }

    public List<EmployeeListDTO> Get_All_Employees(){

        List<Employee> employee_entities = employeeRepo.findAll();
        List<EmployeeListDTO> employeeList_DTOS = Converter.Convert(employee_entities, EmployeeListDTO.class);
        for (int i = 0; i < employeeList_DTOS.size(); i++){

            Employee emp = employee_entities.get(i);
            EmployeeListDTO emp_DTO = employeeList_DTOS.get(i);

            emp_DTO.setDepartment_name(emp.getDepartment().getName());
            emp_DTO.setManager_name(emp.getManager() != null ? emp.getManager().getName() : "");


        }

        return employeeList_DTOS;
    }

    public EmployeeDetailsDTO Get_Employee_Details_By_Id(Long id){

        Employee emp = employeeRepo.findEmployeeById(id);
        EmployeeDetailsDTO emp_details = Converter.Convert(emp, EmployeeDetailsDTO.class);

        emp_details.setPending_leave_request_count((leaveRequestService.Get_Pending_Leave_Request_Count_By_Employee(id)));
        emp_details.setCurrent_month_absent_count(attendanceService.Get_Absent_Count_By_Employee(id));
        emp_details.setManager_name(emp.getManager().getName());
        emp_details.setSalary((payrollService.Get_Payroll_By_Employee(id)).getSalary());
        emp_details.setDepartment_name(emp.getDepartment().getName());
        emp_details.setManager_name(emp.getManager().getName());
        emp_details.setAverage_ratings(performanceReviewService.Get_Average_Rating_By_Employee(id));
//        emp_details.setImage();
        emp_details.setYearly_ratings(performanceReviewService.Get_Performance_Graph_Data_By_Employee(id));

        return emp_details;
    }


    public boolean Create_Employee(EmployeeDTO employee){
        employee.setActive_status(1);
        Employee emp = Converter.Convert(employee,Employee.class);
        var decision = employeeRepo.save(emp);
        return decision.getId() > 0;
    }






}
