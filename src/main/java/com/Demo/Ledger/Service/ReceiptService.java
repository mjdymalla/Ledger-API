package com.Demo.Ledger.Service;

import com.Demo.Ledger.Entity.Receipt;
import com.Demo.Ledger.Repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReceiptService {

    @Autowired
    ReceiptRepository receiptRepository;

    public List<Receipt> getReceiptsInRange(LocalDateTime n) { return receiptRepository.getReceiptsInRange(n); }

    public void saveOrUpdate(Receipt receipt) { receiptRepository.save(receipt); }

    public List<Receipt> getTenantReceipts(int id) { return receiptRepository.findTenantReceipts(id); }
}
