package com.example.retail;

import com.example.retail.pojo.Customer;
import com.example.retail.pojo.ISummary;
import com.example.retail.pojo.Receipt;
import com.example.retail.repository.CustomerRepository;
import com.example.retail.repository.ReceiptRepository;
import com.example.retail.service.CustomerService;
import com.example.retail.service.ReceiptService;
import com.example.retail.service.impl.CustomerServiceImpl;
import com.example.retail.service.impl.ReceiptServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;


import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RetailApplicationTests {

    private final CustomerService customerService;

    private final CustomerRepository customerRepository;

    private final ReceiptRepository receiptRepository;

    private final ReceiptService receiptService;

    public RetailApplicationTests(){
        customerRepository = mock(CustomerRepository.class);
        receiptRepository = mock(ReceiptRepository.class);
        this.customerService = new CustomerServiceImpl(customerRepository, receiptRepository);
        this.receiptService = new ReceiptServiceImpl(receiptRepository);
    }
    @Test
    public void testInsertCustomer() {
        Customer customer = new Customer((long)1, "John", "Zen", new ArrayList<>());
        when(customerRepository.save(customer)).thenReturn(customer);
        assertEquals((long)1, customerService.insertCustomer(customer));
    }

    @Test
    public void testGetCusById(){
        Customer customer = new Customer((long)1, "John", "Zen", new ArrayList<>());
        when(customerRepository.findById((long)1)).thenReturn(Optional.of(customer));
        assertEquals("John", customerService.getCustomerById((long)1).getFirstName());
        assertEquals("Zen", customerService.getCustomerById((long)1).getLastName());
    }

    @Test
    public void testInsertReceiptForCus(){
        Customer customer = new Customer((long)9, "John", "Zen", new ArrayList<>());
        Receipt receipt = new Receipt((long)1, customer, 130, 0, new Date());
        customer.getReceiptList().add(receipt);

        when(receiptRepository.save(receipt)).thenReturn(receipt);
        when(customerRepository.findById((long)9)).thenReturn(Optional.of(customer));

        assertEquals((long)1,customerService.insertReceiptForCustomer((long)9,receipt));

        when(receiptRepository.findById((long)1)).thenReturn(Optional.of(receipt));
        assertEquals(110,receiptService.getReceiptById((long)1).getReward());
    }

    @Test
    public void testGetSummary(){
        Customer customer = new Customer((long)9, "John", "Zen", new ArrayList<>());

        SummaryImpl s1 = new SummaryImpl("130","110", "2022-12");
        SummaryImpl s2 = new SummaryImpl("150", "150", "2023-01");

        when(receiptRepository.getRewardSummary((long)9)).thenReturn(Arrays.asList(s1,s2));
        when(customerRepository.findById((long)9)).thenReturn(Optional.of(customer));

        assertEquals(260, customerService.getSummary((long)9, "2022-11", "2023-02").getTotalReward());
        assertEquals("John", customerService.getSummary((long)9, "2022-11", "2023-02").getFirstName());
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class SummaryImpl implements ISummary{

        private String amount;

        private String reward;

        private String date;
        @Override
        public String getAmount() {
            return amount;
        }

        @Override
        public String getReward() {
            return reward;
        }

        @Override
        public String getDate() {
            return date;
        }
    }

}
