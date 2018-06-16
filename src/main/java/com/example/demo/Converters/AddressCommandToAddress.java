package com.example.demo.Converters;

import com.example.demo.Commands.AddressCommand;
import com.example.demo.Domain.Address;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AddressCommandToAddress implements Converter<AddressCommand,Address> {

    @Synchronized
    @Nullable
    @Override
    public Address convert(AddressCommand addressCommand) {
        if(addressCommand==null)
            return null;

        final Address address=new Address();
        address.setCity(addressCommand.getCity());
        address.setHouseNumber(addressCommand.getHouseNumber());
        address.setId(addressCommand.getId());
        address.setStreet(addressCommand.getStreet());
        address.setZipCode(addressCommand.getZipCode());
        return address;
    }
}
