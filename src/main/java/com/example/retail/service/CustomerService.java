package com.example.retail.service;

import com.example.retail.pojo.Customer;
import com.example.retail.pojo.ISummary;
import com.example.retail.pojo.Receipt;
import com.example.retail.pojo.dto.SummaryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    Long insertCustomer(Customer customer);

    Customer getCustomerById(Long id);

    Long insertReceiptForCustomer(Long id, Receipt receipt);

    SummaryDTO getSummary(Long id, String start, String end);
    //Lazy loading
    //eager loading
}
