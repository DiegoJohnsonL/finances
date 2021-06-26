package com.upc.finances.domain.service;

import com.upc.finances.domain.model.Invoice;
import com.upc.finances.resource.SaveInvoiceWithEffectiveRateResource;
import com.upc.finances.resource.SaveInvoiceWithNominalRateResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface InvoiceService {
    Page<Invoice> getAllInvoices(Pageable pageable);
    Page<Invoice> getAllInvoicesByUserId(Long userId, Pageable pageable);
    Invoice getInvoiceByUserIdAndId(Long userId, Long invoiceId);
    Invoice createInvoice(Long userId, Invoice resource);
    ResponseEntity<?> deleteInvoice(Long userId, Long invoiceId);
}
