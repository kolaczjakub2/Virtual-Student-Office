package com.example.demo.Commands;

import lombok.Data;

@Data
public class AddressCommand {

    public int Id;
    public String City;
    public String ZipCode;
    public String Street;
    public int HouseNumber;
}
