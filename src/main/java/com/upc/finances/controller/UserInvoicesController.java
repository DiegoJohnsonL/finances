package com.upc.finances.controller;
import com.upc.finances.domain.model.*;
import com.upc.finances.domain.service.InvoiceService;
import com.upc.finances.resource.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/invoices/effective-rate")
    public InvoiceResource createInvoiceWithEffectiveRate(@PathVariable(name = "userId") Long userId, @RequestBody SaveInvoiceWithEffectiveRateResource resource){
        return convertToResource(invoiceService.createInvoice(userId, convertToEntity(resource)));
    }
    @PostMapping("/invoices/nominal-rate")
    public InvoiceResource createInvoiceWithNominalRate(@PathVariable(name = "userId") Long userId, @RequestBody SaveInvoiceWithNominalRateResource resource){
        return convertToResource(invoiceService.createInvoice(userId, convertToEntity(resource)));
    }
    @DeleteMapping("/invoices/{invoiceId}")
    public ResponseEntity<?> deleteInvoice(@PathVariable(name = "invoiceId") Long invoiceId, @PathVariable(name = "userId") Long userId){
        return invoiceService.deleteInvoice(userId, invoiceId);
    }
    private Invoice convertToEntity(SaveInvoiceWithEffectiveRateResource resource) {
        Rate rate = new Rate();
        rate.setDaysPerYear(resource.getDaysPerYear());
        rate.setRatePeriod(resource.getRatePeriod());
        rate.setDiscountDate(resource.getDiscountDate());
        rate.setRate(resource.getRate());
        return Invoice.builder()
                .title(resource.getTitle())
                .operationType(resource.getOperationType())
                .issueDate(resource.getIssueDate())
                .paymentDate(resource.getPaymentDate())
                .currency(resource.getCurrency())
                .totalCharged(resource.getTotalCharged())
                .retention(resource.getRetention())
                .rate(rate)
                .result(resource.getResult())
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
        Rate rate = new Rate();
        rate.setDaysPerYear(resource.getDaysPerYear());
        rate.setRatePeriod(resource.getRatePeriod());
        rate.setDiscountDate(resource.getDiscountDate());
        rate.setRate(resource.getRate());
        rate.setCapitalizationPeriod(resource.getCapitalizationPeriod());
        return Invoice.builder()
                .title(resource.getTitle())
                .operationType(resource.getOperationType())
                .issueDate(resource.getIssueDate())
                .paymentDate(resource.getPaymentDate())
                .currency(resource.getCurrency())
                .totalCharged(resource.getTotalCharged())
                .retention(resource.getRetention())
                .rate(rate)
                .result(resource.getResult())
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
                .title(entity.getTitle())
                .operationType(entity.getOperationType())
                .issueDate(entity.getIssueDate())
                .paymentDate(entity.getPaymentDate())
                .currency(entity.getCurrency())
                .totalCharged(entity.getTotalCharged())
                .retention(entity.getRetention())
                .daysPerYear(entity.getRate().getDaysPerYear())
                .ratePeriod(entity.getRate().getRatePeriod())
                .discountDate(entity.getRate().getDiscountDate())
                .rate(entity.getRate().getRate())
                .capitalizationPeriod(entity.getRate().getCapitalizationPeriod())
                .result(entity.getResult())
                .initialCosts(entity.getCosts().stream().filter(invoice -> invoice.getCostType() == CostType.INITIAL).collect(Collectors.toList()))
                .finalCosts(entity.getCosts().stream().filter(invoice -> invoice.getCostType() == CostType.FINAL).collect(Collectors.toList()))
                .build();
    }
}
