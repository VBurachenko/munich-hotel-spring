package com.burachenko.munichhotel.converter.impl;

import com.burachenko.munichhotel.converter.DtoDboConverter;
import com.burachenko.munichhotel.dbo.Booking;
import com.burachenko.munichhotel.dbo.Invoice;
import com.burachenko.munichhotel.dbo.Room;
import com.burachenko.munichhotel.dbo.User;
import com.burachenko.munichhotel.dto.BookingDto;
import com.burachenko.munichhotel.dto.InvoiceDto;
import com.burachenko.munichhotel.dto.RoomDto;
import com.burachenko.munichhotel.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BookingConverter implements DtoDboConverter<Booking, BookingDto> {

    private final UserConverter userConverter;
    private final InvoiceConverter invoiceConverter;
    private final RoomConverter roomConverter;

    @Autowired
    public BookingConverter(UserConverter userConverter,
                            InvoiceConverter invoiceConverter,
                            RoomConverter roomConverter) {
        this.userConverter = userConverter;
        this.invoiceConverter = invoiceConverter;
        this.roomConverter = roomConverter;
    }

    @Override
    public BookingDto convertToDto(Booking booking) {
        final BookingDto bookingDto = new BookingDto();
        BeanUtils.copyProperties(booking, bookingDto, "invoice", "user", "bookingSet");
        setUserToDto(booking, bookingDto);
        setInvoiceToDto(booking, bookingDto);
        setRoomSetToDto(booking, bookingDto);
        return bookingDto;
    }

    @Override
    public Booking convertToDbo(BookingDto bookingDto) {
        final Booking booking = new Booking();
        BeanUtils.copyProperties(bookingDto, booking, "invoice", "user", "bookingSet");
        setUserToDbo(bookingDto, booking);
        setInvoiceToDbo(bookingDto, booking);
        setRoomSetToDbo(bookingDto, booking);
        return booking;
    }

    private void setUserToDbo(final BookingDto dto, final Booking dbo){
        final UserDto userDto = dto.getUser();
        if (userDto != null){
            userDto.setBookingSet(null);
        }
        final User user = userConverter.convertToDbo(userDto);
        dbo.setUser(user);
    }

    private void setUserToDto(final Booking dbo, final BookingDto dto){
        final User user = dbo.getUser();
        if (user != null){
            user.setBookingSet(null);
        }
        final UserDto userDto = userConverter.convertToDto(user);
        dto.setUser(userDto);
    }

    private void setInvoiceToDbo(final BookingDto dto, final Booking dbo){
        final Invoice invoice = invoiceConverter.convertToDbo(dto.getInvoice());
        dbo.setInvoice(invoice);
    }

    private void setInvoiceToDto(final Booking dbo, final BookingDto dto){
        final InvoiceDto invoiceDto = invoiceConverter.convertToDto(dbo.getInvoice());
        dto.setInvoice(invoiceDto);
    }

    private void setRoomSetToDbo(final BookingDto dto, final Booking dbo){
        final Set<RoomDto> dtoRoomSet = dto.getRoomSet();
        if (dtoRoomSet != null){
            for (RoomDto roomDto : dtoRoomSet){
                roomDto.setBookingSet(null);
            }
        }
        final Set<Room> roomSet = roomConverter.convertToDbo(dtoRoomSet);
        dbo.setRoomSet(roomSet);
    }

    private void setRoomSetToDto(final Booking dbo, final BookingDto dto){
        final Set<Room> roomSet = dbo.getRoomSet();
        if (roomSet != null){
            for (Room room : roomSet){
                room.setBookingSet(null);
            }
        }
        final Set<RoomDto> dtoRoomSet = roomConverter.convertToDto(roomSet);
        dto.setRoomSet(dtoRoomSet);
    }

}
