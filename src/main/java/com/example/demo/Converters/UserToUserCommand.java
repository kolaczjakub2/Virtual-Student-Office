package com.example.demo.Converters;

import com.example.demo.Commands.UserCommand;
import com.example.demo.Domain.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UserToUserCommand implements Converter<User, UserCommand> {


    private final AddressToAddressCommand addressToAddressCommand;
    private final DeclarationToDeclarationCommand declarationToDeclarationCommand;

    public UserToUserCommand(AddressToAddressCommand addressToAddressCommand, DeclarationToDeclarationCommand declarationToDeclarationCommand) {
        this.addressToAddressCommand = addressToAddressCommand;
        this.declarationToDeclarationCommand = declarationToDeclarationCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public UserCommand convert(User source) {
        if (source == null) {
            return null;
        }

        final UserCommand userCommand = new UserCommand();
        userCommand.setAddressCommand(addressToAddressCommand.convert(source.getAddress()));

        if (source.getDeclaration() != null)
            userCommand.setDeclarationCommand(declarationToDeclarationCommand.convert(source.getDeclaration()));

        userCommand.setCardNumber(source.getCardNumber());
        userCommand.setEmail(source.getEmail());
        userCommand.setFirstName(source.getFirstName());
        userCommand.setId(source.getId());
        userCommand.setLastName(source.getLastName());
        userCommand.setPassword(source.getPassword());
        userCommand.setPhoneNumber(source.getPhoneNumber());
        userCommand.setRole(source.getRole());
        return userCommand;
    }
}
