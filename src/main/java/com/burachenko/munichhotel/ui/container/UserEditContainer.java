package com.burachenko.munichhotel.ui.container;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.ui.form.UserEditForm;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class UserEditContainer extends Window {

    private UserEditForm userEditForm;
    private Runnable onClose;

    public UserEditContainer(final UserEditForm userEditForm) {
        this.userEditForm = userEditForm;
        setClosable(false);
        setModal(true);
        setDraggable(false);
        setResizable(false);
        setWidth(80, Unit.PERCENTAGE);
        setHeight(80, Unit.PERCENTAGE);
        setContent(this.userEditForm);
        this.userEditForm.setOnSaveOnClose(this::close);
    }

    public void setOnClose(final Runnable onClose) {
        this.onClose = onClose;
    }

    public void startEdit(final UserDto userDto){
        userEditForm.init(userDto);
        UI.getCurrent().addWindow(this);
    }

    public void detach() {
        super.detach();
        onClose.run();
    }
}
