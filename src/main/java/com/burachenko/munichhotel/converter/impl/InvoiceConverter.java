package com.burachenko.munichhotel.converter.impl;

import com.burachenko.munichhotel.converter.DtoDboConverter;
import com.burachenko.munichhotel.dbo.Invoice;
import com.burachenko.munichhotel.dbo.User;
import com.burachenko.munichhotel.dto.InvoiceDto;
import com.burachenko.munichhotel.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceConverter implements DtoDboConverter<Invoice, InvoiceDto> {

    private final UserConverter userConverter;

    @Autowired
    public InvoiceConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    @Override
    public InvoiceDto convertToDto(Invoice invoice) {
        final InvoiceDto invoiceDto = new InvoiceDto();
        BeanUtils.copyProperties(invoice, invoiceDto, "user");
        setUserToDto(invoice, invoiceDto);
        return invoiceDto;
    }

    @Override
    public Invoice convertToDbo(InvoiceDto invoiceDto) {
        final Invoice invoice = new Invoice();
        BeanUtils.copyProperties(invoiceDto, invoice, "user");
        setUserDtoToDbo(invoiceDto, invoice);
        return invoice;
    }

    private void setUserDtoToDbo(final InvoiceDto dto, final Invoice dbo){
        final UserDto userDto = dto.getUser();
        if (userDto != null){
            userDto.setInvoiceSet(null);
        }
        final User user = userConverter.convertToDbo(userDto);
        dbo.setUser(user);
    }

    private void setUserToDto(final Invoice dbo, final InvoiceDto dto){
        final User user = dbo.getUser();
        if (user != null){
            user.setInvoiceSet(null);
        }
        final UserDto userDto = userConverter.convertToDto(user);
        dto.setUser(userDto);

    }
}
