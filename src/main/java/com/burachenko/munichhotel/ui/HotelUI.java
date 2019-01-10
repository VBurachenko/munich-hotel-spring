package com.burachenko.munichhotel.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI(path = "/vaadin")
@Theme("valo")
public class HotelUI extends UI{

    private final SpringViewProvider viewProvider;

    @Autowired
    public HotelUI(final SpringViewProvider viewProvider) {
        this.viewProvider = viewProvider;
    }

    @Override
    protected void init(final VaadinRequest vaadinRequest) {


    }


}
