package com.example.demo.Domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int Id;
    public String City;
    public String ZipCode;
    public String Street;
    public int HouseNumber;

    public Address() {
    }

    public Address(String city, String zipCode, String street, int houseNumber) {

        City = city;
        ZipCode = zipCode;
        Street = street;
        HouseNumber = houseNumber;
    }
}
