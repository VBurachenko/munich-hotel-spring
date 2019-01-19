package com.burachenko.munichhotel.ui.form;

import com.burachenko.munichhotel.dto.BookingDto;
import com.burachenko.munichhotel.dto.RoomDto;
import com.burachenko.munichhotel.entity.RoomEntity;
import com.burachenko.munichhotel.repository.RoomRepository;
import com.burachenko.munichhotel.service.AbstractService;
import com.burachenko.munichhotel.service.BookingService;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.DateField;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@SpringComponent
@ViewScope
public class BookingEditForm extends AbstractEditForm<BookingDto, BookingService> {

    private DateField checkInField;
    private DateField checkOutField;

    @Autowired
    private AbstractService<RoomDto, RoomEntity, RoomRepository> roomService;

    public BookingEditForm(final BookingDto bookingDto, final BookingService bookingService){
        super(bookingDto, bookingService);
    }

    @Override
    protected void bindEntityFields() {
        bindCheckIn();
        bindCheckOut();
        bindUser();
        bindInvoice();
        bindBookingStatus();
        bindRooms();

        setupDateFields();
    }

    private void bindCheckIn() {
        checkInField = new DateField("Check-in");
        checkInField.setRangeStart(LocalDate.now());
        getBinder().forField(checkInField).bind(BookingDto::getCheckIn, BookingDto::setCheckIn);
        addComponent(checkInField);
    }

    private void bindCheckOut() {
        checkOutField = new DateField("Check-Out");
        checkOutField.setRangeStart(LocalDate.now().plusDays(1L));
        getBinder().forField(checkOutField).bind(BookingDto::getCheckOut, BookingDto::setCheckOut);
        addComponent(checkOutField);
    }

    private void bindUser() {

    }

    private void bindInvoice() {
    }

    private void bindBookingStatus() {
    }

    private void bindRooms() {
//        final ListField<RoomDto> roomsField = new ListField<>(roomService::findAll);
//        getBinder().forField(roomsField).bind(BookingDto::getRoomList, BookingDto::setRoomList);
//        addComponent(roomsField);
    }

    private void setupDateFields() {
        final long minimalAllowedDifference = 1L;
        if (checkInField.isEmpty()){
            checkInField.setValue(LocalDate.now());
        }
        if (checkOutField.isEmpty()){
            checkOutField.setValue(LocalDate.now().plusDays(minimalAllowedDifference));
        }
        checkInField.addValueChangeListener(change -> {
            if (change.getValue().isAfter(checkOutField.getValue())){
                checkOutField.setValue(change.getValue().plusDays(minimalAllowedDifference));
            }
            if (change.getValue().isEqual(checkOutField.getValue())){
                checkOutField.setValue(change.getValue().plusDays(minimalAllowedDifference));
            }
        });
        checkOutField.addValueChangeListener(change -> {
            if (change.getValue().isBefore(checkInField.getValue())){
                checkInField.setValue(change.getValue().minusDays(minimalAllowedDifference));
            }
        });
    }


}
