package com.hr_management_system_backend.dto.token;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TokenDTO {
    private Long id;
    private String token;
    private LocalDateTime expiredAt;

}
