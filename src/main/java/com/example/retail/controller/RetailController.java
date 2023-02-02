package com.example.retail.controller;


import com.example.retail.pojo.Customer;
import com.example.retail.pojo.Order;
import com.example.retail.service.RetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/retail")
public class RetailController {


    private RetailService retailService;

    @Autowired
    public RetailController(RetailService retailService){
        this.retailService = retailService;
    }

    @PostMapping("/customer")
    public ResponseEntity insertCustomer(@RequestBody Customer customer){
        Integer newID = retailService.insertCustomer(customer);
        return new ResponseEntity(newID, HttpStatus.OK);
    }

    @PostMapping("/customer/{id}/order")
    public ResponseEntity insertOrder(@RequestParam Integer id, @RequestBody Order order){
        retailService.insertOrder(id, order);

        //todo
        return null;
    }

    @GetMapping("/rewardSummary/{id}")
    public ResponseEntity getRewardSummary(@RequestParam Integer id){
        retailService.getRewardSummary(id);

        //todo
        return null;
    }

}
