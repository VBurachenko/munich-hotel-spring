package com.burachenko.munichhotel.ui.form;

import com.burachenko.munichhotel.dto.InvoiceDto;
import com.burachenko.munichhotel.service.InvoiceService;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;

@SpringComponent
@ViewScope
public class InvoiceEditFrom extends AbstractEditForm<InvoiceDto, InvoiceService> {


    public InvoiceEditFrom(final InvoiceDto invoiceDto, final InvoiceService invoiceService) {
        super(invoiceDto, invoiceService);
    }

    @Override
    protected void bindEntityFields() {

    }
}
