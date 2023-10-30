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


    //region Employee Converter
    public EmployeeDTO Convert(Employee employee){

        System.out.println("Employee Caught = "+employee);
        System.out.println("Employee ID Caught = "+employee.getId());
        EmployeeDTO emp = new EmployeeDTO();
        emp.setId(employee.getId());
        emp.setName(employee.getName());
        emp.setDate_of_birth(employee.getDate_of_birth());
        emp.setAddress(employee.getAddress());
        emp.setEmail(employee.getEmail());
        emp.setPassword(employee.getPassword());
        emp.setPhone(employee.getPhone());
        emp.setDepartment_id(employee.getDepartment().getId());
        System.out.println("Manager  = "+employee.getManager());
        System.out.println("Manager ID  = "+employee.getManager().getId());
        emp.setManager_id(employee.getManager().getId());
        emp.setType(employee.getType().name().toString());
        emp.setId(employee.getId());

        return emp;
    }

    public Employee Convert(EmployeeDTO employee){

        System.out.println("Caught EMP at the starting of Converter "+employee);
        Employee emp = new Employee();
        emp.setId(employee.getId());
        emp.setName(employee.getName());
        emp.setDate_of_birth(employee.getDate_of_birth());
        emp.setAddress(employee.getAddress());
        emp.setEmail(employee.getEmail());
        emp.setPassword(employee.getPassword());
        emp.setPhone(employee.getPhone());
        System.out.println("Employee Caught in Converter line 57 = "+employee.getId());
        System.out.println("Employee Department ID Caught in Converter line 58 = "+employee.getDepartment_id());
        Department tempDept = departmentRepo.findDepartmentById(employee.getDepartment_id());
        System.out.println("Collected Dept info = "+tempDept);
        emp.setDepartment(tempDept);
        Employee tempManager = employeeRepo.findEmployeeById(employee.getManager_id());
        System.out.println("Collected Manager info = "+tempManager);
        emp.setManager(tempManager);
//        employee.setType(("HR".equals(employee.getType())) ? String.valueOf(Employee.EmployeeType.HR) : String.valueOf(Employee.EmployeeType.Employee));
        emp.setType(Employee.EmployeeType.valueOf(employee.getType()));
        emp.setImage(employee.getImage());
        emp.setActive_status(employee.getActive_status());


        return emp;
    }

    public List<EmployeeDTO> Convert(List<Employee>  employees){
//        List<EmployeeDTO> employee_list =  employees.stream()
//                .map(this::Convert)
//                .collect(Collectors.toList());

        List<EmployeeDTO> employee_list = new ArrayList<>();

        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = Convert(employee,EmployeeDTO.class);
            employee_list.add(employeeDTO);
        }


        return employee_list;
    }


// region Department
    public Department Convert(DepartmentDTO department){
        Department dept = new Department();
        dept.setId(department.getId());
        dept.setName(department.getName());
        Employee tempDepartmentHead = employeeRepo.findEmployeeById(department.getDepartment_head_id());
        System.out.println("Collected Department Head info = "+tempDepartmentHead);
        dept.setDepartment_head(tempDepartmentHead);
        dept.setLocation(department.getLocation());
        dept.setDescription(department.getDescription());
        return dept;
    }

    public DepartmentDTO Convert(Department department){

//        DepartmentDTO dept = new DepartmentDTO();
//        dept.setId(department.getId());
//        dept.setName(department.getName());
//        System.out.println("Department Head Employee Information = "+department.getDepartment_head());
//        System.out.println("Department Head ID = "+department.getDepartment_head().getId());
//        dept.setDepartment_head_id(department.getDepartment_head().getId());
//        dept.setLocation(department.getLocation());
//        dept.setDescription(department.getDescription());

        DepartmentDTO dept = modelMapper.map(department, DepartmentDTO.class);
        return dept;

    }

//    public List<DepartmentDTO> Convert(List<Department> departments){
//        List<DepartmentDTO> department_list = new ArrayList<>();
//
//        for (Department department : departments) {
//            DepartmentDTO departmentDTO = Convert(department);
//            department_list.add(departmentDTO);
//        }
//
//        return department_list;
//    }



// endregion Department


//endregion Employee Converter

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
        // Implement the conversion logic here
        // You may use libraries like ModelMapper or manually map fields

        // For a simple example, let's assume a simple direct mapping
        try {
            TARGET_TYPE targetObject = targetClass.getDeclaredConstructor().newInstance();
            // Perform mapping logic here
            // For direct field mapping, you might use reflection or setters/getters

            return targetObject;
        } catch (Exception e) {
            throw new RuntimeException("Conversion failed: " + e.getMessage());
        }
    }





}
