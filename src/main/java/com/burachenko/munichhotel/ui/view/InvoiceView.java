package com.burachenko.munichhotel.ui.view;

import com.burachenko.munichhotel.dto.InvoiceDto;
import com.burachenko.munichhotel.service.InvoiceService;
import com.vaadin.spring.annotation.SpringView;
import org.springframework.beans.factory.annotation.Autowired;


@SpringView(name = InvoiceView.NAME)
public class InvoiceView extends AbstractEntityView<InvoiceDto, InvoiceService> {

    static final String NAME = "invoice";

    @Autowired
    public InvoiceView(final InvoiceService service) {
        super(service);
    }

    @Override
    protected Class<InvoiceDto> getEntityClass() {
        return InvoiceDto.class;
    }

    @Override
    protected String getSearchFieldPlaceholder() {
        return "invoice id";
    }
}
