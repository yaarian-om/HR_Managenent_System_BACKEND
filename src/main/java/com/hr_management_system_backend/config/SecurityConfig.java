package com.hr_management_system_backend.config;


import com.hr_management_system_backend.authentication.JwtAuthenticationEntryPoint;
import com.hr_management_system_backend.authentication.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@Configuration
//public class SecurityConfig {
//    @Autowired
//    private JwtAuthenticationEntryPoint point;
//    @Autowired
//    private JwtAuthenticationFilter filter;
//
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        // configuration
//        http.csrf(csrf->csrf.disable())
//                .cors(cors->cors.disable())
//                .authorizeHttpRequests(auth->auth.requestMatchers("/home/**").authenticated()
//                        .requestMatchers("/", "/images/**", "/hr/login","/hr/alive").permitAll().anyRequest()
//                        .authenticated())
//                .exceptionHandling(ex->ex.authenticationEntryPoint(point))
//                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
//}
//
//








///*

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
////        Write the Backend Endpoints Where the Security Tokens won't be needed
//        return (web) -> web.ignoring().requestMatchers("/", "/images/**", "/hr/login","/hr/alive");
//    }


//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // configuration
        http.csrf(csrf->csrf.disable())
                .cors(cors->cors.disable())
                .authorizeHttpRequests(auth->auth.requestMatchers("/home/**").authenticated()
                        .requestMatchers("/", "/images/**", "/hr/login","/hr/alive").permitAll().anyRequest()
                        .authenticated())
                .exceptionHandling(ex->ex.authenticationEntryPoint(point))
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}

// */