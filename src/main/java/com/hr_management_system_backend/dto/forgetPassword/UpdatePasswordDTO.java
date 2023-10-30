package com.hr_management_system_backend.dto.forgetPassword;

import lombok.Data;

@Data
public class UpdatePasswordDTO {

    private String email;
    private String password;

}
