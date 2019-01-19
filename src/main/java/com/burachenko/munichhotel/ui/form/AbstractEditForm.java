package com.burachenko.munichhotel.ui.form;

import com.burachenko.munichhotel.dto.AbstractDto;
import com.burachenko.munichhotel.service.AbstractService;
import com.vaadin.data.Binder;
import com.vaadin.data.ReadOnlyHasValue;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;

public abstract class AbstractEditForm<DTO extends AbstractDto, Service extends AbstractService>
        extends FormLayout {

    private final Service service;

    private final DTO dto;

    private Binder<DTO> binder;
    private HorizontalLayout controlsLayout = new HorizontalLayout();
    private Button saveButton;

    public AbstractEditForm(final DTO dto, final Service service) {
        setMargin(true);
        this.dto = dto;
        this.service = service;
        binder = new Binder<>();
        binder.setBean(dto);
        removeAllComponents();
        bindID();
        bindEntityFields();
        setupControls();
    }

    protected abstract void bindEntityFields();

    private void bindID(){
        final Label label = new Label();
        label.setCaption("Id");
        ReadOnlyHasValue<DTO> hasValue = new ReadOnlyHasValue<>(
                dto -> {
                    String labelContent = "-";
                    if (getDto() != null && getDto().getId() != null){
                        labelContent = String.valueOf(getDto().getId());
                    }
                    label.setValue(labelContent);
                });
        binder.forField(hasValue).bind(dto -> dto, null);
        addComponent(label);
    }

    private void setupControls() {
        saveButton = new Button("Save");
        saveButton.addClickListener(click -> {
            try {
                final DTO abstractDto = getDto();
                getBinder().writeBean(abstractDto);
                final DTO savedDto = (DTO) getService().save(abstractDto);
                if (savedDto != null){
                    getBinder().setBean(savedDto);

                } else {
                    Notification.show("Saving error", Notification.Type.ERROR_MESSAGE);
                }
            } catch (ValidationException e) {
                Notification.show("Not valid data", Notification.Type.ERROR_MESSAGE);
            }

        });
        controlsLayout.addComponents(saveButton);
        addComponent(controlsLayout);
    }


    public DTO getDto() {
        return dto;
    }

    public Binder<DTO> getBinder() {
        return binder;
    }

    public Service getService() {
        return service;
    }
}
