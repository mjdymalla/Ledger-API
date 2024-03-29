package com.Demo.Ledger.Controller;

import com.Demo.Ledger.Entity.Tenant;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TenantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Test GET request to return list of all tenants
    @Test
    public void getAllTenants() throws Exception {
        mockMvc.perform(get("/tenants")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[0].name", is("Markus")));
    }

    // Test POST request for adding new tenant
    @Test
    public void insertTenant() throws Exception {
        Tenant test = new Tenant("Mark", new BigDecimal("0"), new BigDecimal("0"));
        test.setPaidToDate(null);
        mockMvc.perform(post("/tenants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(test)))
                .andExpect(status().isOk());
    }

    // Test GET request to return specified tenant given id
    @Test
    public void getTenantById() throws Exception {
        mockMvc.perform(get("/tenants/?=id", 1010)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Markus")));
    }

    // Test GET request on returning all receipts generated within n hours
    // If request is returning correct amount both calls to receiptService and tenantService work
    @Test
    public void getReceiptsInTimeFrame() throws Exception {
        mockMvc.perform(get("/tenants/receipts/100")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
