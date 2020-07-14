package com.Demo.Ledger.Repository;

import com.Demo.Ledger.Entity.Receipt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReceiptRepositoryTest {

    @Autowired
    private ReceiptRepository receiptRepository;

    // Check default size of repo given data.sql
    @Test
    public void getReceipts() {
        Iterable<Receipt> receipts = receiptRepository.findAll();
        assertThat(receipts).hasSize(5);
    }

    // Check if mock receipt exists from data.sql in repo
    @Test
    public void findReceipt() {
        Optional<Receipt> receipt = receiptRepository.findById(1423);
        assertThat(receipt.get().getId()).isEqualTo(1423);
    }

    // Check new receipt exists in repo after insertion (id should be 1 given auto generated)
    @Test
    public void insertTenant() {
        Receipt receipt = new Receipt();
        receiptRepository.save(receipt);
        Optional<Receipt> test = receiptRepository.findById(1);
        assertThat(receipt.getId()).isEqualTo(test.get().getId());
    }
}
