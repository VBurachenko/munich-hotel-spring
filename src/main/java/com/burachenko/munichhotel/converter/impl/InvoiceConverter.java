package com.burachenko.munichhotel.converter.impl;

import com.burachenko.munichhotel.converter.EntityDtoConverter;
import com.burachenko.munichhotel.dto.InvoiceDto;
import com.burachenko.munichhotel.entity.InvoiceEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class InvoiceConverter implements EntityDtoConverter<InvoiceEntity, InvoiceDto> {

    @Override
    public InvoiceDto convertToDto(final InvoiceEntity invoice) {
        final InvoiceDto invoiceDto = new InvoiceDto();
        BeanUtils.copyProperties(invoice, invoiceDto);
        return invoiceDto;
    }

    @Override
    public InvoiceEntity convertToEntity(final InvoiceDto invoiceDto) {
        final InvoiceEntity invoice = new InvoiceEntity();
        BeanUtils.copyProperties(invoiceDto, invoice);
        return invoice;
    }
}
