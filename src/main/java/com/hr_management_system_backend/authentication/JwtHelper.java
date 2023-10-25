package com.hr_management_system_backend.authentication;


import com.hr_management_system_backend.entity.Employee;
import com.hr_management_system_backend.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtHelper {


    @Autowired
    TokenService tokenService;

//    private String secret = "7#xT9$Q2@rP6&yW*5uE!pA8%kL1z#F4vG3";
    private String secret = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";

    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {

        var current_token = tokenService.Get_Token_Details_By_Token(token);
        if(current_token.getExpiredAt() == null){
            return extractExpiration(token).before(new Date());
        }else{
            return false;
        }
    }



    //    Generate Token
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        String token = createToken(claims, username);

        boolean decision = tokenService.Add_Token(token);

        return decision ? token : null;
    }

    //    Create Token
    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // (10 hours in milliseconds)
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    //    Validate Token
    public Boolean validateToken(String token, Employee userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getEmail()) && !isTokenExpired(token));
    }



}
