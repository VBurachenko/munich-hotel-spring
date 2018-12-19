package com.burachenko.munichhotel.repository;

import com.burachenko.munichhotel.dbo.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
