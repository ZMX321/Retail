package com.example.retail.controller;


import com.example.retail.pojo.Customer;
import com.example.retail.pojo.ISummary;
import com.example.retail.pojo.Receipt;
import com.example.retail.pojo.dto.CustomerDTO;
import com.example.retail.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getCustomerById(@PathVariable Long id){
        return new ResponseEntity(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity insertCustomer(@RequestBody Customer customer){
        log.info(customer.toString());
        return new ResponseEntity(customerService.insertCustomer(customer), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/receipt")
    public ResponseEntity insertReceiptForCustomer(@PathVariable Long id, @RequestBody Receipt receipt){
        return new ResponseEntity(customerService.insertReceiptForCustomer(id, receipt), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/summary")
    public ResponseEntity getSummary(@PathVariable Long id,@RequestParam String start, @RequestParam String end){
        return new ResponseEntity(customerService.getSummary(id, start, end), HttpStatus.OK);
    }
}
