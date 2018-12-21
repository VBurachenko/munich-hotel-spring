package com.burachenko.munichhotel.converter.impl;

public class UserConverterTest {

    private final UserConverter userConverter = new UserConverter(
                                                        new BookingConverter(
                                                                    new InvoiceConverter(), new RoomConverter()));

}