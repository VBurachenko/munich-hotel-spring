package com.burachenko.munichhotel.converter.impl;

import com.burachenko.munichhotel.converter.EntityDtoConverter;
import com.burachenko.munichhotel.dto.BookingDto;
import com.burachenko.munichhotel.dto.InvoiceDto;
import com.burachenko.munichhotel.dto.RoomDto;
import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.entity.BookingEntity;
import com.burachenko.munichhotel.entity.InvoiceEntity;
import com.burachenko.munichhotel.entity.RoomEntity;
import com.burachenko.munichhotel.entity.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BookingConverter implements EntityDtoConverter<BookingEntity, BookingDto> {

    private final InvoiceConverter invoiceConverter;
    private final RoomConverter roomConverter;

    @Autowired
    public BookingConverter(final InvoiceConverter invoiceConverter,
                            final RoomConverter roomConverter) {
        this.invoiceConverter = invoiceConverter;
        this.roomConverter = roomConverter;
    }

    @Override
    public BookingDto convertToDto(final BookingEntity booking) {
        final BookingDto bookingDto = new BookingDto();
        removeBookingsFromUserDto(bookingDto);
        BeanUtils.copyProperties(booking, bookingDto, "invoice", "user", "bookingSet");
        setInvoiceToDto(booking, bookingDto);
        setRoomSetToDto(booking, bookingDto);
        return bookingDto;
    }

    @Override
    public BookingEntity convertToEntity(final BookingDto bookingDto) {
        final BookingEntity booking = new BookingEntity();
        removeBookingsFromUserDbo(booking);
        BeanUtils.copyProperties(bookingDto, booking, "invoice", "user", "bookingSet");
        setInvoiceToDbo(bookingDto, booking);
        setRoomSetToDbo(bookingDto, booking);
        return booking;
    }

    private void removeBookingsFromUserDto(final BookingDto dto){
        final UserDto userDto = dto.getUser();
        if (userDto != null){
            userDto.setBookingSet(null);
        }
    }

    private void removeBookingsFromUserDbo(final BookingEntity dbo){
        final UserEntity user = dbo.getUser();
        if (user != null){
            user.setBookingSet(null);
        }
    }

    private void setInvoiceToDbo(final BookingDto dto, final BookingEntity dbo){
        final InvoiceEntity invoice = invoiceConverter.convertToEntity(dto.getInvoice());
        dbo.setInvoice(invoice);
    }

    private void setInvoiceToDto(final BookingEntity dbo, final BookingDto dto){
        final InvoiceDto invoiceDto = invoiceConverter.convertToDto(dbo.getInvoice());
        dto.setInvoice(invoiceDto);
    }

    private void setRoomSetToDbo(final BookingDto dto, final BookingEntity dbo){
        final Set<RoomDto> dtoRoomSet = dto.getRoomSet();
        if (dtoRoomSet != null){
            for (final RoomDto roomDto : dtoRoomSet){
                roomDto.setBookingSet(null);
            }
        }
        final Set<RoomEntity> roomSet = roomConverter.convertToEntity(dtoRoomSet);
        dbo.setRoomSet(roomSet);
    }

    private void setRoomSetToDto(final BookingEntity dbo, final BookingDto dto){
        final Set<RoomEntity> roomSet = dbo.getRoomSet();
        if (roomSet != null){
            for (final RoomEntity room : roomSet){
                room.setBookingSet(null);
            }
        }
        final Set<RoomDto> dtoRoomSet = roomConverter.convertToDto(roomSet);
        dto.setRoomSet(dtoRoomSet);
    }

}
