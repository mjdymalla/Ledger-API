package com.Demo.Ledger.Controller;

import com.Demo.Ledger.Entity.Receipt;
import com.Demo.Ledger.Entity.Tenant;
import com.Demo.Ledger.Service.ReceiptService;
import com.Demo.Ledger.Service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TenantController {

    @Autowired
    TenantService tenantService;

    @Autowired
    ReceiptService receiptService;

    // Return list of all tenants
    @GetMapping("/tenants")
    List<Tenant> getAllTenants() {
        return tenantService.getAllTenants();
    }

    // Return tenant given id
    @GetMapping("/tenants/{id}")
    private Tenant getTenant(@PathVariable("id") int id) {
        return tenantService.getTenantById(id);
    }

    // Create new tenant
    @PostMapping("/tenants")
    private int addTenant(@RequestBody Tenant tenant) {
        tenantService.saveOrUpdate(tenant);
        return tenant.getId();
    }

    // Delete tenant given id
    @DeleteMapping("/tenants/{id}")
    private void deleteTenant(@PathVariable("id") int id) {
        tenantService.delete(id);
    }

    // Get list of tenants with receipt(s) created within n hours
    @GetMapping("/tenants/receipts/{hours}")
    private List<Tenant> getRecentReceipts(@PathVariable("hours") int hours) {
        List<Tenant> tenants = new ArrayList<Tenant>();

        LocalDateTime range = LocalDateTime.now().minus(hours, ChronoUnit.HOURS);
        List<Receipt> receipts = receiptService.getReceiptsInRange(range);

        for (Receipt receipt : receipts) {
            Tenant tenant = tenantService.getTenantById(receipt.getTenantId());
            if (!tenants.contains(tenant)) {
                tenants.add(tenant);
            }
        }
        return tenants;
    }
}
