package com.burachenko.munichhotel.converter;

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

import java.util.List;

@Service
public class BookingConverter extends AbstractConverter<BookingEntity, BookingDto> {

    private final InvoiceConverter invoiceConverter;
    private final RoomConverter roomConverter;
    private final UserConverter userConverter;

    @Autowired
    public BookingConverter(final InvoiceConverter invoiceConverter, final RoomConverter roomConverter, final UserConverter userConverter) {
        this.invoiceConverter = invoiceConverter;
        this.roomConverter = roomConverter;
        this.userConverter = userConverter;
    }

    @Override
    public BookingDto convertToDto(final BookingEntity booking) {

        final BookingDto bookingDto = new BookingDto();

        BeanUtils.copyProperties(booking, bookingDto, "invoice", "userAccount", "roomList");

        setUserAccountToDto(booking, bookingDto);
        setInvoiceToDto(booking, bookingDto);
        setRoomListToDto(booking, bookingDto);

        return bookingDto;
    }

    @Override
    public BookingEntity convertToEntity(final BookingDto bookingDto) {

        final BookingEntity bookingEntity = new BookingEntity();

        BeanUtils.copyProperties(bookingDto, bookingEntity, "invoice", "userAccount", "roomList");

        setUserAccountToEntity(bookingDto, bookingEntity);
        setInvoiceToEntity(bookingDto, bookingEntity);
        setRoomListToEntity(bookingDto, bookingEntity);

        return bookingEntity;
    }

    /*
    set invoice
     */

    private void setInvoiceToEntity(final BookingDto bookingDto, final BookingEntity bookingEntity){
        final InvoiceEntity invoiceEntity = invoiceConverter.convertToEntity(bookingDto.getInvoice());
        bookingEntity.setInvoice(invoiceEntity);
    }

    private void setInvoiceToDto(final BookingEntity bookingEntity, final BookingDto bookingDto){
        final InvoiceDto invoiceDto = invoiceConverter.convertToDto(bookingEntity.getInvoice());
        bookingDto.setInvoice(invoiceDto);
    }

    /*
    set rooms list
     */

    private void setRoomListToEntity(final BookingDto bookingDto, final BookingEntity bookingEntity){
        final List<RoomDto> dtoRoomList = bookingDto.getRoomList();
        if (dtoRoomList != null){
            for (final RoomDto roomDto : dtoRoomList){
                roomDto.setBookingList(null);
            }
        }
        final List<RoomEntity> roomList = roomConverter.convertToEntity(dtoRoomList);
        bookingEntity.setRoomList(roomList);
    }

    private void setRoomListToDto(final BookingEntity bookingEntity, final BookingDto bookingDto){
        final List<RoomEntity> roomList = bookingEntity.getRoomList();
        if (roomList != null){
            for (final RoomEntity room : roomList){
                room.setBookingList(null);
            }
        }
        final List<RoomDto> dtoRoomList = roomConverter.convertToDto(roomList);
        bookingDto.setRoomList(dtoRoomList);
    }

    /*
    set user account
     */

    private void setUserAccountToDto(final BookingEntity bookingEntity, final BookingDto bookingDto){
        final UserDto userDto = userConverter.convertToDto(bookingEntity.getUser());
        bookingDto.setUser(userDto);
    }

    private void setUserAccountToEntity(final BookingDto bookingDto, final BookingEntity bookingEntity){
        final UserEntity userEntity = userConverter.convertToEntity(bookingDto.getUser());
        bookingEntity.setUser(userEntity);
    }

}
