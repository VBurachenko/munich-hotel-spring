package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.converter.InvoiceConverter;
import com.burachenko.munichhotel.dto.BookingDto;
import com.burachenko.munichhotel.dto.InvoiceDto;
import com.burachenko.munichhotel.entity.InvoiceEntity;
import com.burachenko.munichhotel.enumeration.InvoiceStatus;
import com.burachenko.munichhotel.repository.InvoiceRepository;
import com.burachenko.munichhotel.service.util.DatesCalculator;
import com.burachenko.munichhotel.service.util.PaymentCalculator;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService extends AbstractService<InvoiceDto, InvoiceEntity, InvoiceRepository>{

    public InvoiceService(final InvoiceRepository invoiceRepository, final InvoiceConverter invoiceConverter) {
        super(invoiceRepository, invoiceConverter);
    }

    @Override
    protected boolean beforeSave(final InvoiceDto dto) {
        return true;
    }

    public InvoiceDto changeInvoiceStatus(final long invoiceId, final InvoiceStatus status){
        final InvoiceDto invoiceDto = findById(invoiceId);
        if (invoiceDto != null){
            invoiceDto.setStatus(status);
            return save(invoiceDto);
        }
        return null;
    }

    public InvoiceDto performInvoicePayment(final long invoiceId){
        final InvoiceDto invoiceDto = findById(invoiceId);
        if (invoiceDto != null){
            invoiceDto.setIsPayed(true);
        }
        return null;
    }

    private InvoiceDto prepareInvoice(final BookingDto bookingDto){

        final InvoiceDto invoiceDto = new InvoiceDto();

        invoiceDto.setId(bookingDto.getId());

        final int nightsCount = DatesCalculator.nightsCountCalculate(bookingDto.getCheckIn(),
                                                                        bookingDto.getCheckOut());

        final double totalPayment = PaymentCalculator.calculateTotalPayment(nightsCount, bookingDto.getRoomList());

        invoiceDto.setNightsCount(nightsCount);
        invoiceDto.setTotalPayment(totalPayment);

        return save(invoiceDto);
    }
}
