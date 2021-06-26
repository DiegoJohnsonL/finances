package com.upc.finances.service;
import com.upc.finances.domain.model.Invoice;
import com.upc.finances.domain.repository.CostRepository;
import com.upc.finances.domain.repository.InvoiceRepository;
import com.upc.finances.domain.repository.UserRepository;
import com.upc.finances.domain.service.InvoiceService;
import com.upc.finances.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.io.Console;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CostRepository costRepository;

    @Override
    public Page<Invoice> getAllInvoices(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Invoice> getAllInvoicesByUserId(Long userId, Pageable pageable) {
        return invoiceRepository.findByUserId(userId, pageable);
    }

    @Override
    public Invoice getInvoiceByUserIdAndId(Long userId, Long invoiceId) {
        return invoiceRepository.findByUserIdAndId(userId, invoiceId).orElseThrow(() -> new NotFoundException("Invoice not found"));
    }

    @Override
    public Invoice createInvoice(Long userId, Invoice invoice) {
        invoice.setUser(userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found")));
        var costs = invoice.getCosts();
        invoice.setCosts(new ArrayList<>());
        invoiceRepository.save(invoice);
        for (var cost : costs) {
            cost.setInvoice(invoice);
        }
        costRepository.saveAll(costs);
        invoice.setCosts(costs);
        return invoice;
    }

    @Override
    public ResponseEntity<?> deleteInvoice(Long userId, Long invoiceId) {
        return invoiceRepository.findByUserIdAndId(userId, invoiceId).map(invoice -> {
            invoiceRepository.delete(invoice);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Invoice not found with Id " + invoiceId + " and PostId " + userId));
    }
}
