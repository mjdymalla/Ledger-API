package com.Demo.Ledger.Controller;

import com.Demo.Ledger.Entity.Receipt;
import com.Demo.Ledger.Entity.Tenant;
import com.Demo.Ledger.Service.ReceiptService;
import com.Demo.Ledger.Service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
public class ReceiptController {

    @Autowired
    ReceiptService receiptService;

    @Autowired
    TenantService tenantService;

    // Generate a receipt for a tenant
    @PostMapping("/receipt")
    private int generateReceipt(@RequestBody Receipt receipt) {
        Tenant tenant = tenantService.getTenantById(receipt.getTenantId());
        BigDecimal rentCredit = tenant.getRentCredit();
        BigDecimal totalRent = tenant.getRent();

        // If total rent goes negative rent credit can be carried over
        totalRent = totalRent.subtract(rentCredit.add(receipt.getAmount()));

        if (totalRent.compareTo(new BigDecimal("0.00")) <= 0) {
            // Set tenants rent credit after payment (will be 0 unless payment and credit exceeded rent amount)
            tenant.setRentCredit(totalRent.negate());

            // Advance period one week (assuming standard period is one week)
            LocalDate advancePeriod = tenant.getPaidToDate().plus(1, ChronoUnit.WEEKS);
            tenant.setPaidToDate(advancePeriod);

            tenantService.saveOrUpdate(tenant);
            receiptService.saveOrUpdate(receipt);

            return receipt.getId();
        }
        return -1;
    }

    // Get list of receipts for a given tenant
    @GetMapping("/receipt/{tenantId}")
    private List<Receipt> getTenantReceipts(@PathVariable("tenantId") int tenantId) {
        return receiptService.getTenantReceipts(tenantId);
    }
}
