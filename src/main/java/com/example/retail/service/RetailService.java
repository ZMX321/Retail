package com.example.retail.service;

import com.example.retail.pojo.Customer;
import com.example.retail.pojo.Order;
import org.springframework.stereotype.Service;

@Service
public interface RetailService {
    Integer insertCustomer(Customer customer);

    void insertOrder(Integer id, Order order);

    void getRewardSummary(Integer id);
}
