package com.burachenko.munichhotel.ui.view;

import com.burachenko.munichhotel.dto.BookingDto;
import com.burachenko.munichhotel.service.BookingService;
import com.burachenko.munichhotel.ui.form.BookingEditForm;
import com.burachenko.munichhotel.ui.window.AbstractEditWindow;
import com.burachenko.munichhotel.ui.window.BookingEditWindow;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.HorizontalLayout;

@SpringView(name = BookingView.NAME)
public class BookingView extends AbstractEntityView<BookingDto, BookingService> {

    static final String NAME = "booking";

    private static final String SEARCH_PLACEHOLDER = "booking id";

    public BookingView(final BookingService bookingService) {
        super(bookingService);
    }

    @Override
    protected Class<BookingDto> getDtoClass() {
        return BookingDto.class;
    }

    @Override
    protected String getSearchFieldPlaceholder() {
        return SEARCH_PLACEHOLDER;
    }

    @Override
    protected void addMoreInstruments(final HorizontalLayout layout) {

    }

    @Override
    protected AbstractEditWindow<BookingDto> getEditWindow(final BookingDto bookingDto) {
        return new BookingEditWindow(new BookingEditForm(bookingDto));
    }
}
