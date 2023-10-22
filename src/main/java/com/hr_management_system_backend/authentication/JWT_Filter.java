package com.hr_management_system_backend.authentication;

import com.hr_management_system_backend.entity.Employee;
import com.hr_management_system_backend.service.Converter;
import com.hr_management_system_backend.service.EmployeeService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWT_Filter extends OncePerRequestFilter {

    @Autowired
    private JsonWebToken jsonWebToken;
    @Autowired
    private EmployeeService employeeService;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        String email;

        if(token != null && token != ""){
            email = jsonWebToken.extractUsername(token);
            if(email != null){
                Employee emp = Converter.Convert(employeeService.Get_Employee_By_Email(email));
                if(jsonWebToken.validateToken(token,emp)){

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            emp,null,emp.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                }
            }

            filterChain.doFilter(request,response);

        }




    }
}
