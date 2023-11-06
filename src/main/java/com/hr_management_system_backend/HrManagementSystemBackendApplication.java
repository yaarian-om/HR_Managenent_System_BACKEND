package com.hr_management_system_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HrManagementSystemBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrManagementSystemBackendApplication.class, args);
        Data_Insertion.Insert();
    }

}
