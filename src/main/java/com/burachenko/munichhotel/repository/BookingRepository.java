package com.burachenko.munichhotel.repository;

import com.burachenko.munichhotel.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    Set<BookingEntity> getBookingSetByUserId(final long userId);

    Optional<BookingEntity> getBookingByInvoiceId(final long invoiceId);
    List<BookingEntity> findAllByCheckInBeforeAndCheckOutAfter(LocalDate before, LocalDate after);

}
