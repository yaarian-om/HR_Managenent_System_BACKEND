package com.hr_management_system_backend.mapper;

import com.hr_management_system_backend.dto.DepartmentDTO;
import com.hr_management_system_backend.dto.EmployeeDTO;
import com.hr_management_system_backend.dto.TokenDTO;
import com.hr_management_system_backend.entity.Department;
import com.hr_management_system_backend.entity.Employee;
import com.hr_management_system_backend.entity.Token;
import com.hr_management_system_backend.repository.IDepartmentRepo;
import com.hr_management_system_backend.repository.IEmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Converter {

    private final IEmployeeRepo employeeRepo;

    private final IDepartmentRepo departmentRepo;

    private final ModelMapper modelMapper;

    public Converter(IEmployeeRepo employeeRepo, IDepartmentRepo departmentRepo, ModelMapper modelMapper) {
        this.employeeRepo = employeeRepo;
        this.departmentRepo = departmentRepo;
        this.modelMapper = modelMapper;
    }



//region Token Converter
    public static TokenDTO Convert(Token token){
            TokenDTO tk = new TokenDTO();
            tk.setId(token.getId());
            tk.setToken(token.getToken());
            tk.setExpiredAt(token.getExpiredAt());

            return tk;
    }

    public static Token Convert(TokenDTO token){
        Token tk = new Token();
        tk.setId(token.getId());
        tk.setToken(token.getToken());
        tk.setExpiredAt(token.getExpiredAt());

        return tk;
    }

//endregion Token Converter


    public static <SOURCE_TYPE, TARGET_TYPE> List<TARGET_TYPE> Convert(List<SOURCE_TYPE> sourceList, Class<TARGET_TYPE> targetClass) {
        List<TARGET_TYPE> targetList = new ArrayList<>();

        for (SOURCE_TYPE sourceObject : sourceList) {
            TARGET_TYPE targetObject = Convert(sourceObject, targetClass);
            targetList.add(targetObject);
        }

        return targetList;
    }

    public static <SOURCE_TYPE, TARGET_TYPE> TARGET_TYPE Convert(SOURCE_TYPE sourceObject, Class<TARGET_TYPE> targetClass) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(sourceObject, targetClass);
    }





}
