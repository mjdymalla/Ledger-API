package com.Demo.Ledger.Repository;

import com.Demo.Ledger.Entity.Tenant;
import com.Demo.Ledger.Repository.TenantRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TenantRepositoryTest {

    @Autowired
    private TenantRepository tenantRepository;

    // Check default size of repo given data.sql
    @Test
    public void getTenants() {
        Iterable<Tenant> tenants = tenantRepository.findAll();
        assertThat(tenants).hasSize(10);
    }

    // Check if mock tenant exists from data.sql in repo
    @Test
    public void findTenant() {
        Optional<Tenant> tenant = tenantRepository.findById(2210);
        assertThat(tenant.get().getId()).isEqualTo(2210);
    }

    // Check if tenant exists after deleting
    @Test
    public void deleteTenant() {
        Optional<Tenant> tenant = tenantRepository.findById(1010);
        tenantRepository.deleteById(1010);
        assertThat(tenant).isNotIn(tenantRepository);
    }

    // Check new tenant exists in repo after insertion (id should be 1 given auto generated)
    @Test
    public void insertTenant() {
        Tenant tenant = new Tenant("Mark", new BigDecimal("0"), new BigDecimal("0"));
        tenantRepository.save(tenant);
        Optional<Tenant> test = tenantRepository.findById(1);
        assertThat(tenant.getId()).isEqualTo(test.get().getId());
    }




}