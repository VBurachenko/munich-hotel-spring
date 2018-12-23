package com.burachenko.munichhotel.converter.impl;

import com.burachenko.munichhotel.converter.DtoDboConverter;
import com.burachenko.munichhotel.dto.InvoiceDto;
import com.burachenko.munichhotel.entity.Invoice;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class InvoiceConverter implements DtoDboConverter<Invoice, InvoiceDto> {

    @Override
    public InvoiceDto convertToDto(Invoice invoice) {
        final InvoiceDto invoiceDto = new InvoiceDto();
        BeanUtils.copyProperties(invoice, invoiceDto);
        return invoiceDto;
    }

    @Override
    public Invoice convertToDbo(InvoiceDto invoiceDto) {
        final Invoice invoice = new Invoice();
        BeanUtils.copyProperties(invoiceDto, invoice);
        return invoice;
    }
}
