package com.Demo.Ledger.Repository;

import com.Demo.Ledger.Entity.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TenantService {

    @Autowired
    TenantRepository tenantRepository;

    public List<Tenant> getAllTenants() {
        List<Tenant> tenants = new ArrayList<Tenant>();
        tenantRepository.findAll().forEach(tenants::add);
        return tenants;
    }

    public Tenant getTenantById(int id) {
        return tenantRepository.findById(id).get();
    }

    public void saveOrUpdate(Tenant tenant) {
        tenantRepository.save(tenant);
    }

    public void delete(int id) {
        tenantRepository.deleteById(id);
    }
}
