package com.Demo.Ledger.Repository;

import com.Demo.Ledger.Entity.Tenant;
import org.springframework.data.repository.CrudRepository;

public interface TenantRepository extends CrudRepository<Tenant, Integer> {}
