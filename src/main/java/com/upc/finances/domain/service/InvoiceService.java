package com.upc.finances.domain.service;

import com.upc.finances.domain.model.Invoice;
import com.upc.finances.resource.SaveInvoiceResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InvoiceService {
    Page<Invoice> getAllInvoices(Pageable pageable);
    Page<Invoice> getAllInvoicesByUserId(Long userId, Pageable pageable);
    Invoice getInvoiceById(Long invoiceId);
    Invoice createInvoice(SaveInvoiceResource resource);
}
