package com.hr_management_system_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


@Entity
@Setter
@Getter
@Table(name="Employee")
public class Employee implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    /**
     * The input format must be "YYYY-MM-DD" for the < input type="date"> element to work correctly
     * */
    @Column(nullable = true)
    private LocalDate date_of_birth;

    @Column(nullable = true)
    private String address;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @ManyToOne
//    Foreign Key (id) with Department Table
    private Department department;

    @OneToOne(mappedBy = "department_head")
    private Department department_head;

//    Foreign Key (id) of this current Table
    @ManyToOne
    private Employee manager;

    public enum EmployeeType{
        HR,
        Employee
    }
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeType type;

    @Column(nullable = true)
    private String image;

    @Column(nullable = true)
    private int active_status;

    @OneToMany(mappedBy = "employee")
    private List<Benefit> benefitList;

    @OneToMany(mappedBy = "employee")
    private List<Attendance> attendanceList;

    @OneToMany(mappedBy = "employee")
    private List<LeaveRequest> leaveRequestList;

//    @OneToMany(mappedBy = "employee")
//    private List<Login> loginList;

    @OneToOne(mappedBy = "employee")
    private Payroll payroll;

    @OneToMany(mappedBy = "employee")
    private List<PerformanceReview> performanceReviewList;







    /**
     * Default or Implemented Built-in Methods to Apply JWT Security on it
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(type.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
