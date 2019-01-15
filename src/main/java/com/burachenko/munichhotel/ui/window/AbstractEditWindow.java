package com.burachenko.munichhotel.ui.window;

import com.burachenko.munichhotel.dto.AbstractDto;
import com.burachenko.munichhotel.ui.form.AbstractEditForm;
import com.vaadin.ui.Window;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractEditWindow<DTO extends AbstractDto> extends Window {

    @Autowired
    private AbstractEditForm<DTO> editForm;

    public AbstractEditWindow(final AbstractEditForm<DTO> editForm) {
        this.editForm = editForm;
        setClosable(false);
        setModal(true);
        setDraggable(false);
        setResizable(false);
        setWidth(70, Unit.PERCENTAGE);
        setHeight(70, Unit.PERCENTAGE);
        setContent(this.editForm);
    }

    public AbstractEditForm<DTO> getEditForm() {
        return editForm;
    }
}
