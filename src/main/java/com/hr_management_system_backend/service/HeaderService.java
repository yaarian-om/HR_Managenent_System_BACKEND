package com.hr_management_system_backend.service;


import com.hr_management_system_backend.authentication.JwtHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

public class HeaderService {


    private final JwtHelper jwtHelper;

    public HeaderService(JwtHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }


    public String Get_User_Email_by_Header(String header){
        String token;
        String email = null;
        if (header != null && header.startsWith("Bearer")) {
            //looking good
            token = header.substring(7);
            try {
                System.out.println("Token Caught By Authentication Filter line 43 : "+token);
                email = this.jwtHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                System.out.println("Illegal Argument while fetching the username !!");
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
                System.out.println("Given jwt token is expired !!");
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                System.out.println("Some changed has done in token !! Invalid Token");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid Header Value !! ");
        }
        return email;
    }


}
