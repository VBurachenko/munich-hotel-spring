package com.burachenko.munichhotel.ui.window;

import com.burachenko.munichhotel.service.AbstractService;
import com.vaadin.ui.Window;

public class UserAddWindow extends Window {

    private AbstractService service;

    public UserAddWindow(final AbstractService service) {
        this.service = service;
    }

}
