package com.Demo.Ledger.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Tenant {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private BigDecimal rent;
    private BigDecimal rentCredit;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate paidToDate = LocalDate.now();

    public Tenant() {};

    public Tenant(String name, BigDecimal rent, BigDecimal rentCredit) {
        this.name = name;
        this.rent = rent;
        this.rentCredit = rentCredit;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) { this.rent = rent; }

    public void setRentCredit(BigDecimal credit) {
        this.rentCredit = credit;
    }

    public BigDecimal getRentCredit() {
        return rentCredit;
    }

    public void setPaidToDate(LocalDate paidToDate) { this.paidToDate = paidToDate; }

    public LocalDate getPaidToDate() { return paidToDate; }
}
