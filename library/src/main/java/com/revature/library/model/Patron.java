package com.revature.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patron")
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message =  "Name is required")
    private String name;

    @Column(nullable = false, unique = true)
    @Email(message = "Invalid email format")
    private String email;

    private LocalDate memberSince  = LocalDate.now();

    //@OneToMany(mappedBy = "patron", cascade = CascadeType.ALL)
    //private List<Loan> loans = new ArrayList<>();

    public Patron(){}

    public Patron(Long id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public LocalDate getMemberSince(){
        return memberSince;
    }

    public void setMemberSince(LocalDate memberSince){
        this.memberSince = memberSince;
    }

    /*public List<Loan> getLoans(){
        return loans;
    }

    public void setLoans(List<Loan> loans){
        this.loans = loans;
    }*/



}
