package com.burachenko.munichhotel.ui.view;

import com.burachenko.munichhotel.dto.BookingDto;
import com.burachenko.munichhotel.service.BookingService;
import com.vaadin.spring.annotation.SpringView;

@SpringView(name = BookingView.NAME)
public class BookingView extends AbstractEntityView<BookingDto, BookingService> {

    static final String NAME = "booking";

    private static final String SEARCH_PLACEHOLDER = "booking id";

    public BookingView(final BookingService bookingService) {
        super(bookingService);
    }

    @Override
    protected Class<BookingDto> getEntityClass() {
        return BookingDto.class;
    }

    @Override
    protected String getSearchFieldPlaceholder() {
        return SEARCH_PLACEHOLDER;
    }
}
