package com.burachenko.munichhotel.ui.window;

import com.burachenko.munichhotel.service.AbstractService;
import com.vaadin.ui.Window;

public class UserEditWindow extends Window {

    private AbstractService service;

    public UserEditWindow(final AbstractService service) {
        this.service = service;
    }

}
