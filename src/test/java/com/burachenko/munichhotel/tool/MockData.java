package com.burachenko.munichhotel.tool;

import com.burachenko.munichhotel.dto.OrderDto;
import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.entity.BookingEntity;
import com.burachenko.munichhotel.entity.InvoiceEntity;
import com.burachenko.munichhotel.entity.UserEntity;
import com.burachenko.munichhotel.enumeration.BookingStatus;
import com.burachenko.munichhotel.enumeration.InvoiceStatus;
import com.burachenko.munichhotel.enumeration.UserRole;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MockData {

    public static UserEntity userEntity() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(10L);
        userEntity.setEmail("email");
        userEntity.setPassword("11qq22ww33ee");
        userEntity.setName("Name");
        userEntity.setSurname("Surname");
        userEntity.setTelNum("+1111");
        userEntity.setBirthday(LocalDate.of(2000, 12, 12));
        userEntity.setDiscount(0);
        userEntity.setBlocking(0);
        userEntity.setRole(UserRole.CUSTOMER);
        userEntity.setGenderMale(true);
        return userEntity;
    }

    public static UserDto userDto() {
        final UserDto userDto = new UserDto();
        userDto.setId(10L);
        userDto.setEmail("email");
        userDto.setPassword("11qq22ww33ee");
        userDto.setName("Name");
        userDto.setSurname("Surname");
        userDto.setTelNum("+1111");
        userDto.setBirthday(LocalDate.of(2000, 12, 12));
        userDto.setDiscount(0);
        userDto.setBlocking(0);
        userDto.setRole(UserRole.CUSTOMER);
        userDto.setGenderMale(true);
        return userDto;
    }

    public static OrderDto orderDto() {
        OrderDto orderDto = new OrderDto();
        orderDto.setBookingId(1L);
        orderDto.setCheckIn(LocalDate.parse("2019-01-01"));
        orderDto.setCheckOut(LocalDate.parse("2019-02-02"));
        orderDto.setBookingStatus(BookingStatus.REGISTERED);
        orderDto.setUserId(1L);
        orderDto.setTotalPayment(100D);
        orderDto.setInvoiceStatus(InvoiceStatus.INVOICED);
        return orderDto;
    }

    public static List<BookingEntity> bookingEntities() {
        final UserEntity userEntity = new UserEntity();
        final InvoiceEntity invoiceEntityOne = new InvoiceEntity();
        final InvoiceEntity invoiceEntityTwo = new InvoiceEntity();
        final List<BookingEntity> bookingEntities = new ArrayList<>();
        final BookingEntity bookingEntityOne = new BookingEntity();
        final BookingEntity bookingEntityTwo = new BookingEntity();

        userEntity.setId(1L);
        invoiceEntityOne.setId(1L);
        invoiceEntityTwo.setId(2L);

        bookingEntityOne.setId(1L);
        bookingEntityOne.setCheckIn(LocalDate.parse("2019-01-01"));
        bookingEntityOne.setCheckOut(LocalDate.parse("2019-02-02"));
        bookingEntityOne.setStatus(BookingStatus.REGISTERED);
//        bookingEntityOne.setUser(userEntity);
        bookingEntityOne.setInvoice(invoiceEntityOne);
        bookingEntities.add(bookingEntityOne);

        bookingEntityTwo.setId(2L);
        bookingEntityTwo.setCheckIn(LocalDate.parse("2019-02-02"));
        bookingEntityTwo.setCheckOut(LocalDate.parse("2019-03-03"));
        bookingEntityTwo.setStatus(BookingStatus.REGISTERED);
//        bookingEntityTwo.setUser(userEntity);
        bookingEntityTwo.setInvoice(invoiceEntityTwo);
        bookingEntities.add(bookingEntityTwo);

        return bookingEntities;
    }

    public static List<InvoiceEntity> invoiceEntities() {
        final List<InvoiceEntity> invoiceEntities = new ArrayList<>();
        final InvoiceEntity invoiceEntityOne = new InvoiceEntity();
        final InvoiceEntity invoiceEntityTwo = new InvoiceEntity();

        invoiceEntityOne.setId(1L);
        invoiceEntityOne.setTotalPayment(100D);
        invoiceEntityOne.setStatus(InvoiceStatus.INVOICED);
        invoiceEntities.add(invoiceEntityOne);

        invoiceEntityOne.setId(2L);
        invoiceEntityTwo.setTotalPayment(200D);
        invoiceEntityTwo.setStatus(InvoiceStatus.INVOICED);
        invoiceEntities.add(invoiceEntityTwo);

        return invoiceEntities;
    }

    public static InvoiceEntity invoiceEntity() {
        InvoiceEntity invoiceEntity = new InvoiceEntity();
        invoiceEntity.setId(1L);
        invoiceEntity.setStatus(InvoiceStatus.INVOICED);
        invoiceEntity.setTotalPayment(100D);
        invoiceEntity.setAppointment(LocalDate.parse("0001-01-01"));
        invoiceEntity.setIsPayed(true);
        invoiceEntity.setNightsCount(1);
        return invoiceEntity;
    }


}
