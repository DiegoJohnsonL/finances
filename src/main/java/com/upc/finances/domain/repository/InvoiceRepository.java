package com.upc.finances.domain.repository;

import com.upc.finances.domain.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Page<Invoice> findByUserId(Long userId, Pageable pageable);
    Optional<Invoice> findByUserIdAndId(Long userId, Long invoiceId);
}
