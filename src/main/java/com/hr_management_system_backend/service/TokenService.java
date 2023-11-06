package com.hr_management_system_backend.service;

import com.hr_management_system_backend.dto.token.TokenDTO;
import com.hr_management_system_backend.entity.Token;
import com.hr_management_system_backend.mapper.Converter;
import com.hr_management_system_backend.repository.IEmployeeRepo;
import com.hr_management_system_backend.repository.ITokenRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenService {



    private final ITokenRepo tokenRepo;
    private final IEmployeeRepo employeeRepo;

    public TokenService(ITokenRepo tokenRepo, IEmployeeRepo employeeRepo) {
        this.tokenRepo = tokenRepo;
        this.employeeRepo = employeeRepo;
    }

    public TokenDTO Get_Token_Details_By_Token(String token){
        // Previously => return Converter.Convert(tokenRepo.findByToken(token));
        return Converter.Convert(tokenRepo.findByToken(token), TokenDTO.class);
    }

    public boolean Add_Token(String token){

        Token tk = new Token();
        tk.setToken(token);
        tk.setExpiredAt(null);

        var decision = tokenRepo.save(tk);

        return decision.getId() > 0;

    }

    public boolean Expire_Token(String token){

        Token current_token = tokenRepo.findByToken(token);
        current_token.setExpiredAt(LocalDateTime.now());

        var decision = tokenRepo.save(current_token);

        return decision.getId() > 0;

    }





}
