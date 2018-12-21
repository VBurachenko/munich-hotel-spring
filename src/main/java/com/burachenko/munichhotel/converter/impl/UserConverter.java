package com.burachenko.munichhotel.converter.impl;

import com.burachenko.munichhotel.converter.DtoDboConverter;
import com.burachenko.munichhotel.dbo.Booking;
import com.burachenko.munichhotel.dbo.Invoice;
import com.burachenko.munichhotel.dbo.User;
import com.burachenko.munichhotel.dto.BookingDto;
import com.burachenko.munichhotel.dto.InvoiceDto;
import com.burachenko.munichhotel.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserConverter implements DtoDboConverter<User, UserDto> {

    private final BookingConverter bookingConverter;
    private final InvoiceConverter invoiceConverter;

    @Autowired
    public UserConverter(BookingConverter bookingConverter, InvoiceConverter invoiceConverter) {
        this.bookingConverter = bookingConverter;
        this.invoiceConverter = invoiceConverter;
    }

    @Override
    public UserDto convertToDto(final User user) {
        final UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDto, user, "bookingSet", "invoiceSet");
        setBookingSetToDto(user, userDto);
        setInvoiceSetToDto(user, userDto);
        return userDto;
    }

    @Override
    public User convertToDbo(UserDto userDto) {
        final User user = new User();
        BeanUtils.copyProperties(userDto, user, "bookingSet", "invoiceSet");
        setBookingSetToDbo(userDto, user);
        setInvoiceSetToDbo(userDto, user);
        return user;
    }

    private void setBookingSetToDbo(final UserDto dto, final User dbo){
        final Set<BookingDto> dtoBookingSet = dto.getBookingSet();
        if (dtoBookingSet != null){
            for (BookingDto bookingDto : dtoBookingSet) {
                bookingDto.setUser(null);
            }
        }
        final Set<Booking> bookingSet = bookingConverter.convertToDbo(dtoBookingSet);
        dbo.getBookingSet().addAll(bookingSet);
    }

    private void setBookingSetToDto(final User dbo, final UserDto dto){
        final Set<Booking> dboBookingSet = dbo.getBookingSet();
        if (dboBookingSet != null){
            for (Booking booking : dboBookingSet) {
                booking.setUser(null);
            }
        }
        final Set<BookingDto> bookingSet = bookingConverter.convertToDto(dboBookingSet);
        dto.getBookingSet().addAll(bookingSet);
    }

    private void setInvoiceSetToDbo(final UserDto dto, final User dbo){
        final Set<InvoiceDto> dtoInvoiceSet = dto.getInvoiceSet();
        if (dtoInvoiceSet != null){
            for (InvoiceDto invoiceDto : dtoInvoiceSet) {
                invoiceDto.setUser(null);
            }
        }
        final Set<Invoice> invoiceSet = invoiceConverter.convertToDbo(dtoInvoiceSet);
        dbo.getInvoiceSet().addAll(invoiceSet);
    }

    private void setInvoiceSetToDto(final User dbo, final UserDto dto){
        final Set<Invoice> dboInvoiceSet = dbo.getInvoiceSet();
        if (dboInvoiceSet != null){
            for (Invoice invoice : dboInvoiceSet) {
                invoice.setUser(null);
            }
        }
        final Set<InvoiceDto> invoiceSet = invoiceConverter.convertToDto(dboInvoiceSet);
        dto.getInvoiceSet().addAll(invoiceSet);
    }
}
