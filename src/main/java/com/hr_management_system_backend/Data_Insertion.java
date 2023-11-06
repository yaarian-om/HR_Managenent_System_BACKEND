package com.hr_management_system_backend;

import com.hr_management_system_backend.dto.DepartmentDTO;
import com.hr_management_system_backend.dto.employee.EmployeeDTO;
import com.hr_management_system_backend.dto.attendance.AttendanceDTO;
import com.hr_management_system_backend.dto.performance_review.PerformanceReviewDTO;
import com.hr_management_system_backend.dto.leave_request.LeaveRequestDTO_TEMP;
import com.hr_management_system_backend.entity.Employee;
import com.hr_management_system_backend.entity.LeaveRequest;
import com.hr_management_system_backend.mapper.Converter;
import com.hr_management_system_backend.repository.ILeaveRequestRepo;
import com.hr_management_system_backend.service.AttendanceService;
import com.hr_management_system_backend.service.DepartmentService;
import com.hr_management_system_backend.service.EmployeeService;
import com.hr_management_system_backend.service.PerformanceReviewService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Data_Insertion {


    private static EmployeeService employeeService;
    private static DepartmentService departmentService;

    private static AttendanceService attendanceService;
    private static PerformanceReviewService performanceReviewService;

    private static ILeaveRequestRepo leaveRequestRepo;
    public Data_Insertion(EmployeeService employeeService, ILeaveRequestRepo leaveRequestRepo,
                          DepartmentService departmentService,PerformanceReviewService performanceReviewService,
                          AttendanceService attendanceService){
        Data_Insertion.employeeService = employeeService;
        Data_Insertion.leaveRequestRepo = leaveRequestRepo;
        Data_Insertion.departmentService = departmentService;
        Data_Insertion.performanceReviewService = performanceReviewService;
        Data_Insertion.attendanceService = attendanceService;
    }

    public static void Insert(){

//        System.out.println("Insertion Called");
//        create_Employees();
//        Create_Leave_Requests();
//        Create_Department();
//        Create_Performance();
//        Create_Attendance();





    }


    private static void create_Employees() {
        List<EmployeeDTO> employees = Arrays.asList(
                new EmployeeDTO(1L, "Sudipta Kumar Das", "2023-10-20", "78/4 East Rampura Dhaka-1219", "dip.kumar020@gmail.com", "0tempPass@", "01931117419", 1L, null, "HR", "https://avatars.githubusercontent.com/u/83475574?v=4", 0),
                new EmployeeDTO(2L, "John Doe", "1990-05-15", "123 Main Street", "john.doe@example.com", "securePass123", "555-555-5555", 2L, 1L, "Employee", "", 0),
                new EmployeeDTO(3L, "Santi Kumar Das", "1985-08-22", "456 Elm Street", "santi.das@example.com", "strongPass456", "555-123-4567", 2L, 1L, "Employee", "", 0),
                new EmployeeDTO(4L, "Alice Johnson", "1992-03-10", "789 Oak Avenue", "alice.johnson@example.com", "password123", "555-987-6543", 1L, null, "HR", "", 0),
                new EmployeeDTO(5L, "Bob Wilson", "1988-11-05", "321 Pine Road", "bob.wilson@example.com", "secretPass789", "555-555-7890", 3L, 3L, "Employee", "", 0),
                new EmployeeDTO(6L, "Eva Martinez", "1987-02-18", "654 Cedar Lane", "eva.martinez@example.com", "hiddenPass321", "555-123-9876", 2L, null, "HR", "", 0),
                new EmployeeDTO(7L, "David Brown", "1995-07-30", "987 Maple Street", "david.brown@example.com", "strongPassword", "555-789-1234", 3L, 3L, "Employee", "", 0),
                new EmployeeDTO(8L, "Sarah Clark", "1993-04-25", "567 Birch Lane", "sarah.clark@example.com", "secure123Pass", "555-555-5555", 3L, 7L, "Employee", "", 0),
                new EmployeeDTO(9L, "Michael Lee", "1991-09-12", "432 Walnut Road", "michael.lee@example.com", "pass123word", "555-123-4567", 2L, 2L, "Employee", "", 0),
                new EmployeeDTO(10L, "Linda Wilson", "1994-12-03", "876 Spruce Avenue", "linda.wilson@example.com", "secret456Pass", "555-987-6543", 2L, 2L, "Employee", "", 0)
        );

        for (EmployeeDTO employee : employees) {
            employeeService.Create_Employee(employee);
        }
    }

    private static void Create_Leave_Requests(){
        List<LeaveRequestDTO_TEMP> leaveRequests = Arrays.asList(
                new LeaveRequestDTO_TEMP(1L,1L, "Annual Leave", "2023-10-04", "2023-10-06", "Accepted", "Annual leave for a short vacation."),
                new LeaveRequestDTO_TEMP(2L, 2L,"Sick Leave", "2023-11-10", "2023-11-12", "Pending", "Medical leave due to illness."),
                new LeaveRequestDTO_TEMP(3L, 3L,"Vacation", "2023-12-20", "2023-12-25", "Accepted", "Holiday vacation with family."),
                new LeaveRequestDTO_TEMP(4L, 4L,"Maternity Leave", "2023-09-15", "2024-03-14", "Pending", "Maternity leave for pregnancy."),
                new LeaveRequestDTO_TEMP(5L, 5L,"Unpaid Leave", "2023-10-28", "2023-11-01", "Rejected", "Leave without pay for personal reasons."),
                new LeaveRequestDTO_TEMP(6L, 6L,"Emergency Leave", "2023-10-07", "2023-10-08", "Accepted", "Emergency leave due to family issues."),
                new LeaveRequestDTO_TEMP(7L, 7L,"Personal Leave", "2023-11-05", "2023-11-07", "Pending", "Leave for personal matters."),
                new LeaveRequestDTO_TEMP(8L, 8L,"Study Leave", "2023-12-01", "2024-01-31", "Rejected", "Leave for pursuing further studies."),
                new LeaveRequestDTO_TEMP(9L, 9L,"Bereavement Leave", "2023-10-18", "2023-10-19", "Accepted", "Leave to attend a funeral."),
                new LeaveRequestDTO_TEMP(10L, 10L,"Annual Leave", "2023-11-25", "2023-11-29", "Pending", "Annual leave for a vacation.")
        );

        for (LeaveRequestDTO_TEMP leaveRequest : leaveRequests) {
            Employee emp = employeeService.Get_Employee_By_Id(leaveRequest.getEmployee_id());
            LeaveRequest leave_conv = Converter.Convert(leaveRequest, LeaveRequest.class);
            leave_conv.setEmployee(emp);
            leaveRequestRepo.save(leave_conv);
        }
    }

    private static void Create_Department(){
        List<DepartmentDTO> departments = Arrays.asList(
                new DepartmentDTO(1L,"Human Resource",1L,"Dhaka","The HR department is dedicated to managing and developing the organization's most valuable asset – its people"),
                new DepartmentDTO(2L,"Development",3L,"Dhaka","The Development department is at the forefront of innovation and growth"),
                new DepartmentDTO(3L,"Testing",4L,"Dhaka","The Testing department plays a crucial role in maintaining the quality and reliability of our products")
        );

        for (DepartmentDTO department : departments) {
            departmentService.Create_Department(department);
        }
    }

    private static void Create_Performance(){
        List<PerformanceReviewDTO> performanceReviewDTOS = Arrays.asList(
                new PerformanceReviewDTO(1L, 2L, "2023/01/01", 4.5, "Great work"),
                new PerformanceReviewDTO(2L, 2L, "2023/02/01", 4.2, "Excellent performance"),
                new PerformanceReviewDTO(3L, 2L, "2023/03/01", 4.8, "Outstanding"),
                new PerformanceReviewDTO(4L, 2L, "2023/04/01", 4.3, "Very satisfied"),
                new PerformanceReviewDTO(5L, 2L, "2023/05/01", 4.7, "Impressive"),
                new PerformanceReviewDTO(6L, 2L, "2023/06/01", 4.1, "Good job"),
                new PerformanceReviewDTO(7L, 2L, "2023/07/01", 4.4, "Satisfactory"),
                new PerformanceReviewDTO(8L, 2L, "2023/08/01", 4.9, "Exceptional"),
                new PerformanceReviewDTO(9L, 2L, "2023/09/01", 4.6, "Impressive work"),
                new PerformanceReviewDTO(10L, 2L, "2023/10/01", 4.2, "Very good"),
                new PerformanceReviewDTO(11L, 2L, "2023/11/01", 4.5, "Excellent"),
                new PerformanceReviewDTO(12L, 2L, "2023/12/01", 4.3, "Very satisfied"),
                new PerformanceReviewDTO(13L, 3L, "2023/01/01", 4.0, "Satisfactory"),
                new PerformanceReviewDTO(14L, 3L, "2023/02/01", 4.7, "Impressive"),
                new PerformanceReviewDTO(15L, 3L, "2023/03/01", 4.1, "Good job"),
                new PerformanceReviewDTO(16L, 3L, "2023/04/01", 4.5, "Excellent"),
                new PerformanceReviewDTO(17L, 3L, "2023/05/01", 4.8, "Exceptional"),
                new PerformanceReviewDTO(18L, 3L, "2023/06/01", 4.4, "Very good"),
                new PerformanceReviewDTO(19L, 3L, "2023/07/01", 4.2, "Great work"),
                new PerformanceReviewDTO(20L, 3L, "2023/08/01", 4.9, "Outstanding"),
                new PerformanceReviewDTO(21L, 3L, "2023/09/01", 4.3, "Very satisfied"),
                new PerformanceReviewDTO(22L, 3L, "2023/10/01", 4.6, "Impressive work"),
                new PerformanceReviewDTO(23L, 3L, "2023/11/01", 4.7, "Impressive"),
                new PerformanceReviewDTO(24L, 3L, "2023/12/01", 4.2, "Very good"),
                new PerformanceReviewDTO(25L, 5L, "2023/01/01", 4.4, "Satisfactory"),
                new PerformanceReviewDTO(26L, 5L, "2023/02/01", 4.1, "Good job"),
                new PerformanceReviewDTO(27L, 5L, "2023/03/01", 4.7, "Impressive"),
                new PerformanceReviewDTO(28L, 5L, "2023/04/01", 4.3, "Very satisfied"),
                new PerformanceReviewDTO(29L, 5L, "2023/05/01", 4.6, "Impressive work"),
                new PerformanceReviewDTO(30L, 5L, "2023/06/01", 4.0, "Satisfactory"),
                new PerformanceReviewDTO(31L, 5L, "2023/07/01", 4.8, "Exceptional"),
                new PerformanceReviewDTO(32L, 5L, "2023/08/01", 4.5, "Excellent"),
                new PerformanceReviewDTO(33L, 5L, "2023/09/01", 4.2, "Very good"),
                new PerformanceReviewDTO(34L, 5L, "2023/10/01", 4.9, "Outstanding"),
                new PerformanceReviewDTO(35L, 5L, "2023/11/01", 4.4, "Satisfactory"),
                new PerformanceReviewDTO(36L, 5L, "2023/12/01", 4.1, "Good job"),
                new PerformanceReviewDTO(37L, 7L, "2023/01/01", 3.8, "Good"),
                new PerformanceReviewDTO(38L, 7L, "2023/02/01", 3.9, "Above average"),
                new PerformanceReviewDTO(39L, 7L, "2023/03/01", 3.7, "Fair"),
                new PerformanceReviewDTO(40L, 7L, "2023/04/01", 4.0, "Satisfactory"),
                new PerformanceReviewDTO(41L, 7L, "2023/05/01", 3.5, "Fair"),
                new PerformanceReviewDTO(42L, 7L, "2023/06/01", 4.2, "Good work"),
                new PerformanceReviewDTO(43L, 7L, "2023/07/01", 3.6, "Average"),
                new PerformanceReviewDTO(44L, 7L, "2023/08/01", 3.9, "Above average"),
                new PerformanceReviewDTO(45L, 7L, "2023/09/01", 3.7, "Fair"),
                new PerformanceReviewDTO(46L, 7L, "2023/10/01", 4.0, "Satisfactory"),
                new PerformanceReviewDTO(47L, 7L, "2023/11/01", 3.8, "Good"),
                new PerformanceReviewDTO(48L, 7L, "2023/12/01", 3.6, "Average"),
                new PerformanceReviewDTO(49L, 8L, "2023/01/01", 4.5, "Excellent"),
                new PerformanceReviewDTO(50L, 8L, "2023/02/01", 4.2, "Very good"),
                new PerformanceReviewDTO(51L, 8L, "2023/03/01", 4.9, "Outstanding"),
                new PerformanceReviewDTO(52L, 8L, "2023/04/01", 3.8, "Good"),
                new PerformanceReviewDTO(53L, 8L, "2023/05/01", 4.5, "Impressive"),
                new PerformanceReviewDTO(54L, 8L, "2023/06/01", 4.0, "Satisfactory"),
                new PerformanceReviewDTO(55L, 8L, "2023/07/01", 3.5, "Fair"),
                new PerformanceReviewDTO(56L, 8L, "2023/08/01", 4.1, "Good job"),
                new PerformanceReviewDTO(57L, 8L, "2023/09/01", 4.4, "Satisfactory"),
                new PerformanceReviewDTO(58L, 8L, "2023/10/01", 4.7, "Impressive"),
                new PerformanceReviewDTO(59L, 8L, "2023/11/01", 4.2, "Very good"),
                new PerformanceReviewDTO(60L, 8L, "2023/12/01", 4.5, "Excellent"),
                new PerformanceReviewDTO(61L, 9L, "2023/01/01", 4.0, "Satisfactory"),
                new PerformanceReviewDTO(62L, 9L, "2023/02/01", 4.7, "Impressive"),
                new PerformanceReviewDTO(63L, 9L, "2023/03/01", 4.1, "Good job"),
                new PerformanceReviewDTO(64L, 9L, "2023/04/01", 4.5, "Excellent"),
                new PerformanceReviewDTO(65L, 9L, "2023/05/01", 4.8, "Exceptional"),
                new PerformanceReviewDTO(66L, 9L, "2023/06/01", 4.4, "Very good"),
                new PerformanceReviewDTO(67L, 9L, "2023/07/01", 4.2, "Great work"),
                new PerformanceReviewDTO(68L, 9L, "2023/08/01", 4.9, "Outstanding"),
                new PerformanceReviewDTO(69L, 9L, "2023/09/01", 4.3, "Very satisfied"),
                new PerformanceReviewDTO(70L, 9L, "2023/10/01", 4.6, "Impressive work"),
                new PerformanceReviewDTO(71L, 9L, "2023/11/01", 4.7, "Impressive"),
                new PerformanceReviewDTO(72L, 9L, "2023/12/01", 4.2, "Very good"),
                new PerformanceReviewDTO(73L, 10L, "2023/01/01", 3.5, "Fair"),
                new PerformanceReviewDTO(74L, 10L, "2023/02/01", 3.7, "Fair"),
                new PerformanceReviewDTO(75L, 10L, "2023/03/01", 3.8, "Good"),
                new PerformanceReviewDTO(76L, 10L, "2023/04/01", 3.6, "Average"),
                new PerformanceReviewDTO(77L, 10L, "2023/05/01", 3.9, "Above average"),
                new PerformanceReviewDTO(78L, 10L, "2023/06/01", 3.7, "Fair"),
                new PerformanceReviewDTO(79L, 10L, "2023/07/01", 4.0, "Satisfactory"),
                new PerformanceReviewDTO(80L, 10L, "2023/08/01", 3.5, "Fair"),
                new PerformanceReviewDTO(81L, 10L, "2023/09/01", 4.2, "Good work"),
                new PerformanceReviewDTO(82L, 10L, "2023/10/01", 3.6, "Average"),
                new PerformanceReviewDTO(83L, 10L, "2023/11/01", 3.8, "Good"),
                new PerformanceReviewDTO(84L, 10L, "2023/12/01", 3.9, "Above average")
        );

        for (PerformanceReviewDTO performanceReviewDTO : performanceReviewDTOS) {
            performanceReviewService.Create_Performance(performanceReviewDTO);
        }
    }

    private static void Create_Attendance(){
        List<AttendanceDTO> attendances = Arrays.asList(
                new AttendanceDTO(1L, 2L, "2023/10/01", "09:00:00.000", "17:00:00.000"),
                new AttendanceDTO(2L, 3L, "2023/10/02", "09:15:00.000", "17:30:00.000"),
                new AttendanceDTO(3L, 5L, "2023/10/03", "08:45:00.000", "16:45:00.000"),
                new AttendanceDTO(4L, 7L, "2023/10/04", "10:00:00.000", "18:00:00.000"),
                new AttendanceDTO(5L, 8L, "2023/10/05", "08:30:00.000", "16:30:00.000"),
                new AttendanceDTO(6L, 9L, "2023/10/06", "09:30:00.000", "17:30:00.000"),
                new AttendanceDTO(7L, 10L, "2023/10/07", "09:00:00.000", "17:00:00.000"),
                new AttendanceDTO(8L, 2L, "2023/10/08", "09:10:00.000", "17:15:00.000"),
                new AttendanceDTO(9L, 3L, "2023/10/09", "08:50:00.000", "16:55:00.000"),
                new AttendanceDTO(10L, 5L, "2023/10/10", "09:20:00.000", "17:25:00.000"),
                new AttendanceDTO(11L, 7L, "2023/10/11", "09:15:00.000", "17:20:00.000"),
                new AttendanceDTO(12L, 8L, "2023/10/12", "09:05:00.000", "17:10:00.000"),
                new AttendanceDTO(13L, 9L, "2023/10/13", "09:25:00.000", "17:30:00.000"),
                new AttendanceDTO(14L, 10L, "2023/10/14", "09:30:00.000", "17:35:00.000")
        );

        for (AttendanceDTO attendance : attendances) {
            attendanceService.Create_Attendance(attendance);
        }

    }





}
