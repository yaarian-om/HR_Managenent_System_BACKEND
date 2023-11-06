package com.hr_management_system_backend.mapper;


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
