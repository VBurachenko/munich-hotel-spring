package com.burachenko.munichhotel.ui.window;

import com.burachenko.munichhotel.dto.BookingDto;
import com.burachenko.munichhotel.service.BookingService;
import com.burachenko.munichhotel.ui.form.BookingEditForm;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@ViewScope
public class BookingEditWindow extends AbstractEditWindow<BookingDto, BookingService> {

    @Autowired
    public BookingEditWindow(final BookingEditForm bookingEditForm) {
        super(bookingEditForm);
    }
}
