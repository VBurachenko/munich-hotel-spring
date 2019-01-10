package com.burachenko.munichhotel.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;

@SpringView(name = BookingView.NAME)
public class BookingView implements View {

    static final String NAME = "booking";

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {


    }
}
