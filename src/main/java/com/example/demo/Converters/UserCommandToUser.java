package com.example.demo.Converters;

import com.example.demo.Commands.UserCommand;
import com.example.demo.Domain.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UserCommandToUser implements Converter<UserCommand, User> {

    private final AddressCommandToAddress addressCommandToAddress;
    private final DeclarationCommandToDeclaration declarationCommandToDeclaration;

    public UserCommandToUser(AddressCommandToAddress addressCommandToAddress, DeclarationCommandToDeclaration declarationCommandToDeclaration) {
        this.addressCommandToAddress = addressCommandToAddress;
        this.declarationCommandToDeclaration = declarationCommandToDeclaration;
    }

    @Synchronized
    @Nullable
    @Override
    public User convert(UserCommand source) {
        if (source == null) {
            return null;
        }

        final User user = new User();
        user.setCardNumber(source.getCardNumber());
        user.setEmail(source.getEmail());
        user.setFirstName(source.getFirstName());
        user.setId(source.getId());
        user.setLastName(source.getLastName());
        user.setPassword(source.getPassword());
        user.setPhoneNumber(source.getPhoneNumber());
        user.setRole(source.getRole());
        user.setAddress(addressCommandToAddress.convert(source.getAddressCommand()));

        if (source.getDeclarationCommand() != null)
            user.setDeclaration(declarationCommandToDeclaration.convert(source.getDeclarationCommand()));
        return user;
    }
}

