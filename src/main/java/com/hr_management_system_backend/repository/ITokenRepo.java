package com.hr_management_system_backend.repository;

import com.hr_management_system_backend.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITokenRepo extends JpaRepository<Token, Long> {
    Token findByToken(String token);
}
