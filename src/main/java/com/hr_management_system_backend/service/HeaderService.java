package com.hr_management_system_backend.service;


import com.hr_management_system_backend.authentication.JwtHelper;
import com.hr_management_system_backend.entity.Employee;
import com.hr_management_system_backend.repository.IEmployeeRepo;
import org.springframework.stereotype.Service;

@Service
public class HeaderService {


    private final JwtHelper jwtHelper;
    private final IEmployeeRepo employeeRepo;

    public HeaderService(JwtHelper jwtHelper, IEmployeeRepo employeeRepo) {
        this.jwtHelper = jwtHelper;
        this.employeeRepo = employeeRepo;
    }


    public Long Get_User_Id_By_Request_Header(String requestHeader){
        if (requestHeader != null && requestHeader.startsWith("Bearer")) {
            //looking good
            String token = "";
            String email;
            token = requestHeader.substring(7);
            try {
                System.out.println("Token Caught By Authentication Filter line 43 : "+token);
                email = this.jwtHelper.getUsernameFromToken(token);
                Employee emp = employeeRepo.findByEmail(email);
                return emp.getId();
            } catch (Exception ex){
                System.out.println("Error found in TokenService.java = "+ex.getMessage());
                return -1L;
            }
        }
        return -1L;
    }

    public Employee Get_User_By_Request_Header(String requestHeader){
        if (requestHeader != null && requestHeader.startsWith("Bearer")) {
            //looking good
            String token = "";
            String email;
            token = requestHeader.substring(7);
            try {
                System.out.println("Token Caught By Authentication Filter line 43 : "+token);
                email = this.jwtHelper.getUsernameFromToken(token);
                Employee emp = employeeRepo.findByEmail(email);
                return emp;
            } catch (Exception ex){
                System.out.println("Error found in TokenService.java = "+ex.getMessage());
                return null;
            }
        }
        return null;
    }

    public String Get_User_Email_By_Request_Header(String requestHeader){
        if (requestHeader != null && requestHeader.startsWith("Bearer")) {
            //looking good
            String token = "";
            String email;
            token = requestHeader.substring(7);
            try {
                System.out.println("Token Caught By Authentication Filter line 43 : "+token);
                email = this.jwtHelper.getUsernameFromToken(token);
                return email;
            } catch (Exception ex){
                System.out.println("Error found in TokenService.java = "+ex.getMessage());
                return null;
            }
        }
        return null;
    }


}
