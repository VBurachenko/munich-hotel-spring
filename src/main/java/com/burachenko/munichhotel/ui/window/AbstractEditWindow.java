package com.burachenko.munichhotel.ui.window;

import com.burachenko.munichhotel.dto.AbstractDto;
import com.burachenko.munichhotel.service.AbstractService;
import com.burachenko.munichhotel.ui.form.AbstractEditForm;
import com.vaadin.ui.Window;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractEditWindow<DTO extends AbstractDto, Service extends AbstractService> extends Window {

    @Autowired
    private AbstractEditForm<DTO, Service> editForm;

    public AbstractEditWindow(final AbstractEditForm<DTO, Service> editForm) {
        this.editForm = editForm;
        setModal(true);
        setDraggable(false);
        setResizable(false);
        setWidth(70, Unit.PERCENTAGE);
        setHeight(70, Unit.PERCENTAGE);
        setContent(this.editForm);
        setScrollLeft(0);
    }

    public AbstractEditForm<DTO, Service> getEditForm() {
        return editForm;
    }
}
