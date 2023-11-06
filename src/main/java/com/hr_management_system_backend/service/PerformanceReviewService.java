package com.hr_management_system_backend.service;

import com.hr_management_system_backend.dto.performance_review.PerformanceReviewDTO;
import com.hr_management_system_backend.dto.performance_review.PerformanceReviewDetailsDTO;
import com.hr_management_system_backend.dto.performance_review.PerformanceReviewListDTO;
import com.hr_management_system_backend.entity.Employee;
import com.hr_management_system_backend.entity.PerformanceReview;
import com.hr_management_system_backend.mapper.Converter;
import com.hr_management_system_backend.repository.IPerformanceReviewRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceReviewService {

    private final IPerformanceReviewRepo performanceReviewRepo;
    private final EmployeeService employeeService;


    public PerformanceReviewService(IPerformanceReviewRepo performanceReviewRepo, EmployeeService employeeService) {
        this.performanceReviewRepo = performanceReviewRepo;
        this.employeeService = employeeService;
    }



    public List<PerformanceReviewListDTO> Get_All_Performance_Review(){

        List<PerformanceReview> performanceReviewList = performanceReviewRepo.findAll();
        List<PerformanceReviewListDTO> performanceReviewListDTO = Converter.Convert(performanceReviewList, PerformanceReviewListDTO.class);
        for (int i = 0; i < performanceReviewListDTO.size(); i++) {
            PerformanceReview performanceReview = performanceReviewList.get(i);
            PerformanceReviewListDTO performanceReviewDTO = performanceReviewListDTO.get(i);

            performanceReviewDTO.setName(performanceReview.getEmployee().getName());
            performanceReviewDTO.setEmail(performanceReview.getEmployee().getEmail());




        }
        return performanceReviewListDTO;
    }

    public PerformanceReviewDetailsDTO Get_Performance_By_Id(Long id){

        PerformanceReview specific_performance = performanceReviewRepo.findPerformanceReviewById(id);

        PerformanceReviewDetailsDTO performanceDetailsDTO = Converter.Convert(specific_performance, PerformanceReviewDetailsDTO.class);
        performanceDetailsDTO.setName(specific_performance.getEmployee().getName());

        List<PerformanceReview> performanceReviewList = performanceReviewRepo.findPerformanceReviewsByEmployee(specific_performance.getEmployee());

        double avg_rating = 0;
        for(PerformanceReview single_performance : performanceReviewList){
            avg_rating = avg_rating + single_performance.getRating();
        }
        avg_rating = (avg_rating / performanceReviewList.size());


        performanceDetailsDTO.setAverage_rating(avg_rating);

        return performanceDetailsDTO;
    }

    public boolean Create_Performance(PerformanceReviewDTO performanceReviewDTO){


        Employee emp = employeeService.Get_Employee_By_Id(performanceReviewDTO.getEmployee_id());
        PerformanceReview performance = Converter.Convert(performanceReviewDTO, PerformanceReview.class);
        performance.setEmployee(emp);
        var decision = performanceReviewRepo.save(performance);

        return decision.getId() != null;
    }



}
