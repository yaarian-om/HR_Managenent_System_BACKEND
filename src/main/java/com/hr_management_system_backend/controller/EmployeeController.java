package com.hr_management_system_backend.controller;

import com.hr_management_system_backend.dto.employee.EmployeeDTO;
import com.hr_management_system_backend.dto.employee.EmployeeDetailsDTO;
import com.hr_management_system_backend.dto.employee.EmployeeListDTO;
import com.hr_management_system_backend.service.EmployeeService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images";

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    //region Employee
    @GetMapping("/get_all_employee_list")
    public ResponseEntity<List<EmployeeListDTO>> Get_All_Employees(){
//        Show Employees who has active status 1
        List<EmployeeListDTO> employeeListDTOS = employeeService.Get_All_Employees();

        if(employeeListDTOS.size() > 0){
            return ResponseEntity.ok(employeeListDTOS);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get_single_employee/{id}")
    public ResponseEntity<EmployeeDetailsDTO> Get_Employee_by_Id(@PathVariable Long id){
        EmployeeDetailsDTO emp_details = employeeService.Get_Employee_Details_By_Id(id);
        if(emp_details.getId() > 0){
            return ResponseEntity.ok(emp_details);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get_ex_employee_list")
    public ResponseEntity<Object> Get_All_Ex_Employees(){
//        Show Employees who has active status 0
        return null;
    }

    @PostMapping("/create/employee")
    public ResponseEntity<Object> Create_Employee(@RequestBody EmployeeDTO employee){
        boolean decision = employeeService.Create_Employee(employee);
        if (decision){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/store_profile_picture")
    public String uploadImage(@RequestHeader("Authorization") String header, Model model, @RequestParam("image") MultipartFile file) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, filename);
//        fileNames.append(file.getOriginalFilename());
        fileNames.append(filename);
        Files.write(fileNameAndPath, file.getBytes());
        employeeService.Upload_Image(header, filename);
        model.addAttribute("msg", "Uploaded images: " + fileNames.toString());
        return fileNameAndPath.toString();
    }


    @GetMapping("/get_profile_picture")
    public ResponseEntity<Resource> getImage(@RequestHeader("Authorization") String header) {
        String imageName = employeeService.Get_Profile_Image_Name(header);
        File imageFile = new File(UPLOAD_DIRECTORY, imageName);

        if (imageFile.exists()) {
            try {
                HttpHeaders headers = new HttpHeaders();

                // Determine the content type based on the image's file extension
                String fileExtension = imageName.substring(imageName.lastIndexOf(".") + 1).toLowerCase();
                MediaType mediaType = getMediaTypeForImageExtension(fileExtension);
                headers.setContentType(mediaType);

                // Set the "Content-Disposition" header to make the browser treat the response as an attachment
                headers.setContentDispositionFormData("attachment", imageName);

                byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
                Resource resource = new ByteArrayResource(imageBytes);

                return ResponseEntity.ok()
                        .headers(headers)
                        .body(resource);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private MediaType getMediaTypeForImageExtension(String fileExtension) {
        switch (fileExtension) {
            case "jpeg":
            case "jpg":
                return MediaType.IMAGE_JPEG;
            case "png":
                return MediaType.IMAGE_PNG;
            case "gif":
                return MediaType.IMAGE_GIF;
            // Add more cases for other image types as needed
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }






    @PutMapping("/update/employee")
    public ResponseEntity<Object> Update_Employee_Info(@RequestBody EmployeeDTO employee){
        return null;
    }

    @DeleteMapping("/delete/employee/{id}")
    public ResponseEntity<Object> Delete_Employee(@PathVariable Long id){
//        Change Active Status to 0;
        return null;
    }


//endregion Employees

    @GetMapping("/get_hr_dashboard")
    public ResponseEntity<Object> Show_HR_Dashboard(@RequestHeader("Authorization") String header){
        return null;
    }

    @GetMapping("/get_employee_dashboard")
    public ResponseEntity<Object> Show_Employee_Dashboard(@RequestHeader("Authorization") String header){
        return null;
    }

    @GetMapping("/get_my_profile")
    public ResponseEntity<Object> Show_My_Profile(@RequestHeader("Authorization") String header){
        return null;
    }




}
