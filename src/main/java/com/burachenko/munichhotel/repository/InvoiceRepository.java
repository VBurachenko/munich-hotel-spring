package com.burachenko.munichhotel.repository;

import com.burachenko.munichhotel.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
    List<InvoiceEntity> findByIdAndIsPayed(final Long id, final Boolean isPayed);

    List<InvoiceEntity> findByIdIn(final List<Long> ids);
}
