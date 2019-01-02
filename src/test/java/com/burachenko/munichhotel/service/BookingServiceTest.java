package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.dto.OrderDto;
import com.burachenko.munichhotel.entity.InvoiceEntity;
import com.burachenko.munichhotel.repository.BookingRepository;
import com.burachenko.munichhotel.repository.InvoiceRepository;
import com.burachenko.munichhotel.tool.MockData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private BookingService bookingService;

    @Test
    public void findOrders() {
        Mockito.when(bookingRepository.getByCheckInBetweenAndStatus(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(MockData.bookingEntities());
        Mockito.when(invoiceRepository.findByIdIn(Mockito.anyList())).thenReturn(MockData.invoiceEntities());

        final List<OrderDto> orderDtos = bookingService.findOrders("0001-01-01", "0001-01-01", "REGISTERED");

        assertEquals(2, orderDtos.size());

        OrderDto orderDto = MockData.orderDto();
        assertEquals(orderDto.getUserId(), orderDtos.get(0).getUserId());
        assertEquals(orderDto.getBookingId(), orderDtos.get(0).getBookingId());
        assertEquals(orderDto.getBookingStatus(), orderDtos.get(0).getBookingStatus());
        assertEquals(orderDto.getCheckIn(), orderDtos.get(0).getCheckIn());
        assertEquals(orderDto.getCheckOut(), orderDtos.get(0).getCheckOut());
        assertEquals(orderDto.getInvoiceStatus(), orderDtos.get(0).getInvoiceStatus());
        assertEquals(orderDto.getTotalPayment(), orderDtos.get(0).getTotalPayment());
    }

    @Test
    public void getMoneyAmount() {
        List<InvoiceEntity> invoiceEntities = new ArrayList<>();
        invoiceEntities.add(MockData.invoiceEntity());
        Mockito.when(bookingRepository.getByCheckInBetween(Mockito.any(), Mockito.any())).thenReturn(MockData.bookingEntities());
        Mockito.when(invoiceRepository.findByIdAndIsPayed(Mockito.any(), Mockito.anyBoolean())).thenReturn(invoiceEntities);

        final Double sum = bookingService.getMoneyAmount("0001-01-01", "0001-01-01");
        final Double answer = 200D;

        assertEquals(answer, sum);
    }
}