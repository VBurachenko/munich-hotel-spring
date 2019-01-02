package com.burachenko.munichhotel.repository;

import com.burachenko.munichhotel.entity.BookingEntity;
import com.burachenko.munichhotel.enumeration.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    Set<BookingEntity> getBookingSetByUserId(final long userId);

    Optional<BookingEntity> getBookingByInvoiceId(final long invoiceId);

    List<BookingEntity> getByCheckInBetweenAndStatus(final LocalDate checkIn, final LocalDate checkIn2, final BookingStatus status);

    List<BookingEntity> getByCheckInBetween(LocalDate from, LocalDate to);
}
