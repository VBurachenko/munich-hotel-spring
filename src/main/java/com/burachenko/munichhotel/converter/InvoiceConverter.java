package com.burachenko.munichhotel.converter;

import com.burachenko.munichhotel.dto.InvoiceDto;
import com.burachenko.munichhotel.entity.InvoiceEntity;
import com.burachenko.munichhotel.enumeration.InvoicePaymentStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class InvoiceConverter extends AbstractConverter<InvoiceEntity, InvoiceDto> {

    @Override
    public InvoiceDto convertToDto(final InvoiceEntity invoice) {
        final InvoiceDto invoiceDto = new InvoiceDto();
        BeanUtils.copyProperties(invoice, invoiceDto, "isPayed");
        invoiceDto.setIsPayed(convertIsPayed(invoice.getIsPayed()));
        return invoiceDto;
    }

    @Override
    public InvoiceEntity convertToEntity(final InvoiceDto invoiceDto) {
        final InvoiceEntity invoice = new InvoiceEntity();
        BeanUtils.copyProperties(invoiceDto, invoice, "isPayed");
        invoice.setIsPayed(convertIsPayed(invoiceDto.getIsPayed()));
        return invoice;
    }

    private boolean convertIsPayed(final InvoicePaymentStatus isPayed){
        return isPayed.equals(InvoicePaymentStatus.PAYED);
    }

    private InvoicePaymentStatus convertIsPayed(final boolean isPayed){
        if (isPayed){
            return InvoicePaymentStatus.PAYED;
        }
        return InvoicePaymentStatus.NOT_PAYED;
    }
}
