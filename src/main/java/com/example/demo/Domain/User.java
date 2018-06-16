package com.example.demo.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long Id;
    public String FirstName;
    public String LastName;
    public int cardNumber;
    public String Password;
    public int PhoneNumber;
    public String Email;
    @OneToOne(cascade = CascadeType.ALL)
    public Address Address;

    private String role;
    @OneToOne(cascade = CascadeType.ALL)
    private Declaration declaration;

    public User(String firstName, String lastName, int cardNumber, String password, int phoneNumber, String email,String role, com.example.demo.Domain.Address address, Declaration declaration) {
        FirstName = firstName;
        LastName = lastName;
        this.cardNumber = cardNumber;
        Password = password;
        PhoneNumber = phoneNumber;
        Email = email;
        Address = address;
        this.role=role;
        this.declaration = declaration;
    }

    public User() {
    }

}
