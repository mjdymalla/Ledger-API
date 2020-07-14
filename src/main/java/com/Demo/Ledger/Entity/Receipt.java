package com.Demo.Ledger.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Receipt {

    @Id
    @GeneratedValue
    private int id;
    private int tenantId;
    private BigDecimal amount;

    // Should be time stamped when instantiated
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime created = LocalDateTime.now();

    public int getId() { return id; }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreated() { return created; }

    public void setCreated(LocalDateTime created) { this.created = created;}
}
