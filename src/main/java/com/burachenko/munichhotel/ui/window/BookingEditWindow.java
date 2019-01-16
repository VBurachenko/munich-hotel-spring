package com.burachenko.munichhotel.ui.window;

import com.burachenko.munichhotel.dto.BookingDto;
import com.burachenko.munichhotel.ui.form.BookingEditForm;
import org.springframework.beans.factory.annotation.Autowired;

public class BookingEditWindow extends AbstractEditWindow<BookingDto> {

    @Autowired
    public BookingEditWindow(final BookingEditForm bookingEditForm) {
        super(bookingEditForm);
    }
}
