package com.burachenko.munichhotel.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = BookingView.NAME)
public class BookingView extends VerticalLayout implements View {

    static final String NAME = "booking";

    public BookingView() {
    }

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {

    }
}
