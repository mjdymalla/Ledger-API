package com.Demo.Ledger.Controller;

import com.Demo.Ledger.Entity.Receipt;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReceiptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Test for generating receipt - uses both receiptService and tenantService
    // Given data.sql test is provided existing tenant and should return 1
    @Test
    public void createReceipt() throws Exception {
        Receipt test = new Receipt();
        test.setTenantId(1010);
        test.setAmount(new BigDecimal("500"));
        test.setCreated(null);

        mockMvc.perform(post("/receipt")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(test)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(1)));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
