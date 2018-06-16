package com.example.demo.Commands;

import lombok.Data;

@Data
public class UserCommand {

    public Long Id;
    public String FirstName;
    public String LastName;
    public int CardNumber;
    public String Password;
    public int PhoneNumber;
    public String Email;
    public String Role;

    public AddressCommand AddressCommand;
    public DeclarationCommand declarationCommand;
}
