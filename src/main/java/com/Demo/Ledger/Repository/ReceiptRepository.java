package com.Demo.Ledger.Repository;

import com.Demo.Ledger.Entity.Receipt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReceiptRepository extends CrudRepository<Receipt, Integer> {
    @Query("SELECT u FROM Receipt u WHERE u.tenantId = ?1")
    List<Receipt> findTenantReceipts(int id);

    @Query("SELECT u FROM Receipt u WHERE u.created > ?1")
    List<Receipt> getReceiptsInRange(LocalDateTime n);
}
