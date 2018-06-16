package com.example.demo.Converters;

import com.example.demo.Commands.AddressCommand;
import com.example.demo.Domain.Address;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AddressToAddressCommand implements Converter<Address, AddressCommand> {

    @Synchronized
    @Nullable
    @Override
    public AddressCommand convert(Address address) {
        if (address == null)
            return null;

        final AddressCommand addressCommand = new AddressCommand();
        addressCommand.setCity(address.getCity());
        addressCommand.setHouseNumber(address.getHouseNumber());
        addressCommand.setId(address.getId());
        addressCommand.setStreet(address.getStreet());
        addressCommand.setZipCode(address.getZipCode());
        return addressCommand;
    }
}
