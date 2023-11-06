package com.hr_management_system_backend.service;

import com.hr_management_system_backend.dto.performance_review.PerformanceReviewDTO;
import com.hr_management_system_backend.dto.performance_review.PerformanceReviewDetailsDTO;
import com.hr_management_system_backend.dto.performance_review.PerformanceReviewListDTO;
import com.hr_management_system_backend.entity.Employee;
import com.hr_management_system_backend.entity.PerformanceReview;
import com.hr_management_system_backend.mapper.Converter;
import com.hr_management_system_backend.repository.IEmployeeRepo;
import com.hr_management_system_backend.repository.IPerformanceReviewRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PerformanceReviewService {

    private final IPerformanceReviewRepo performanceReviewRepo;
    private final IEmployeeRepo employeeRepo;


    public PerformanceReviewService(IPerformanceReviewRepo performanceReviewRepo, IEmployeeRepo employeeRepo) {
        this.performanceReviewRepo = performanceReviewRepo;
        this.employeeRepo = employeeRepo;
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




        performanceDetailsDTO.setAverage_rating(Calculate_Average_Rating(performanceReviewList));

        return performanceDetailsDTO;
    }

    public double Get_Average_Rating_By_Employee(Long employee_id){

        List<PerformanceReview> performanceReviewList = performanceReviewRepo.findPerformanceReviewsByEmployee(employeeRepo.findEmployeeById(employee_id));
        return Calculate_Average_Rating(performanceReviewList);

    }

    public double[] Get_Performance_Graph_Data_By_Employee(Long employee_id){
        List<PerformanceReview> performanceReviewList = performanceReviewRepo.findPerformanceReviewsByEmployee(employeeRepo.findEmployeeById(employee_id));
        return Get_Yearly_Ratings_By_Employee(performanceReviewList);
    }






    public boolean Create_Performance(PerformanceReviewDTO performanceReviewDTO){


        Employee emp = employeeRepo.findEmployeeById(performanceReviewDTO.getEmployee_id());
        PerformanceReview performance = Converter.Convert(performanceReviewDTO, PerformanceReview.class);
        performance.setEmployee(emp);
        var decision = performanceReviewRepo.save(performance);

        return decision.getId() != null;
    }

    private double Calculate_Average_Rating(List<PerformanceReview> performanceReviewList){
        double avg_rating = 0;
        for(PerformanceReview single_performance : performanceReviewList){
            avg_rating = avg_rating + single_performance.getRating();
        }
        avg_rating = (avg_rating / performanceReviewList.size());
        return  avg_rating;
    }

    private double[] Get_Yearly_Ratings_By_Employee(List<PerformanceReview> performanceReviewList) {
        // Initialize a map to store the total ratings and counts for each month
        Map<String, Double> monthlyTotalRatings = new HashMap<>();
        Map<String, Integer> monthlyReviewCounts = new HashMap<>();

        // Define a date formatter for parsing "yyyy-MM-dd" dates
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Iterate through the PerformanceReview list
        for (PerformanceReview review : performanceReviewList) {
            // Parse the review date
            LocalDate reviewDate = LocalDate.parse(review.getReviewDate(), dateFormatter);

            // Extract the year and month from the review date
            int year = reviewDate.getYear();
            int month = reviewDate.getMonthValue();
            String monthYearKey = String.format("%04d-%02d", year, month);

            // Update the total ratings and review counts for the month
            double rating = review.getRating();
            monthlyTotalRatings.put(monthYearKey, monthlyTotalRatings.getOrDefault(monthYearKey, 0.0) + rating);
            monthlyReviewCounts.put(monthYearKey, monthlyReviewCounts.getOrDefault(monthYearKey, 0) + 1);
        }

        // Calculate the average ratings for each month and store in a double[] array
        List<Double> monthlyAverageRatings = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            for (int year = 2023; year <= 2023; year++) { // Modify the years as needed
                String monthYearKey = String.format("%04d-%02d", year, month);
                double totalRatings = monthlyTotalRatings.getOrDefault(monthYearKey, 0.0);
                int reviewCount = monthlyReviewCounts.getOrDefault(monthYearKey, 0);
                double averageRating = (reviewCount > 0) ? totalRatings / reviewCount : 0.0;
                monthlyAverageRatings.add(averageRating);
            }
        }

        // Convert the List<Double> to double[]
        double[] yearlyRatingsArray = new double[monthlyAverageRatings.size()];
        for (int i = 0; i < monthlyAverageRatings.size(); i++) {
            yearlyRatingsArray[i] = monthlyAverageRatings.get(i);
        }

        return yearlyRatingsArray;
    }



}
