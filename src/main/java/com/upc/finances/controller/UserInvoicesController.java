package com.upc.finances.controller;
import com.upc.finances.domain.model.Cost;
import com.upc.finances.domain.model.CostType;
import com.upc.finances.domain.model.Invoice;
import com.upc.finances.domain.service.InvoiceService;
import com.upc.finances.resource.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@CrossOrigin
@RestController
@RequestMapping("/api/users/{userId}")
public class UserInvoicesController {
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/invoices")
    public Page<InvoiceResource> getAllInvoicesByUserId(@PathVariable(name = "userId") Long userId, Pageable pageable){
        List<InvoiceResource> resource = invoiceService.getAllInvoicesByUserId(userId, pageable).stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resource, pageable, resource.size());
    }
    @GetMapping("/invoices/{invoiceId}")
    public InvoiceResource getInvoiceByIdAndUserId(@PathVariable(name = "invoiceId") Long invoiceId, @PathVariable(name = "userId") Long userId){
        return convertToResource(invoiceService.getInvoiceByUserIdAndId(userId, invoiceId));
    }
    @PutMapping("/invoices/effective-rate")
    public InvoiceResource createInvoiceWithEffectiveRate(@PathVariable(name = "userId") Long userId, @RequestBody SaveInvoiceWithEffectiveRateResource resource){
        return convertToResource(invoiceService.createInvoice(userId, convertToEntity(resource)));
    }
    @PutMapping("/invoices/nominal-rate")
    public InvoiceResource createInvoiceWithNominalRate(@PathVariable(name = "userId") Long userId, @RequestBody SaveInvoiceWithNominalRateResource resource){
        return convertToResource(invoiceService.createInvoice(userId, convertToEntity(resource)));
    }
    private Invoice convertToEntity(SaveInvoiceWithEffectiveRateResource resource) {
        return Invoice.builder()
                .issueDate(resource.getIssueDate())
                .paymentDate(resource.getPaymentDate())
                .totalCharged(resource.getTotalCharged())
                .retention(resource.getRetention())
                .rate(resource.getRate())
                .costs(Stream.concat(resource.getInitialCosts().stream().map(saveCostResource -> {
                    Cost cost = mapper.map(saveCostResource, Cost.class);
                    cost.setCostType(CostType.INITIAL);
                    return cost;
                }), resource.getFinalCosts().stream().map(saveCostResource -> {
                    Cost cost = mapper.map(saveCostResource, Cost.class);
                    cost.setCostType(CostType.FINAL);
                    return cost;
                })).collect(Collectors.toList()))
                .build();
    }

    private Invoice convertToEntity(SaveInvoiceWithNominalRateResource resource) {
        return Invoice.builder()
                .issueDate(resource.getIssueDate())
                .paymentDate(resource.getPaymentDate())
                .totalCharged(resource.getTotalCharged())
                .retention(resource.getRetention())
                .rate(resource.getNominalRate())
                .costs(Stream.concat(resource.getInitialCosts().stream().map(saveCostResource -> {
                    Cost cost = mapper.map(saveCostResource, Cost.class);
                    cost.setCostType(CostType.INITIAL);
                    return cost;
                }), resource.getFinalCosts().stream().map(saveCostResource -> {
                    Cost cost = mapper.map(saveCostResource, Cost.class);
                    cost.setCostType(CostType.FINAL);
                    return cost;
                })).collect(Collectors.toList()))
                .build();
    }

    private InvoiceResource convertToResource(Invoice entity) {
        return InvoiceResource.builder()
                .id(entity.getId())
                .issueDate(entity.getIssueDate())
                .paymentDate(entity.getPaymentDate())
                .totalCharged(entity.getTotalCharged())
                .retention(entity.getRetention())
                .rate(entity.getRate())
                .initialCosts(entity.getCosts().stream().filter(invoice -> invoice.getCostType() == CostType.INITIAL).collect(Collectors.toList()))
                .finalCosts(entity.getCosts().stream().filter(invoice -> invoice.getCostType() == CostType.FINAL).collect(Collectors.toList()))
                .build();
    }
}
