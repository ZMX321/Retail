package com.example.retail.controller;


import com.example.retail.pojo.Receipt;
import com.example.retail.pojo.dto.ReceiptDTO;
import com.example.retail.service.ReceiptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Order;

@RestController
@RequestMapping("/receipt")
@Slf4j
public class ReceiptController {


    private ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService){
        this.receiptService = receiptService;
    }


    @GetMapping("/{id}")
    public ResponseEntity getReceiptById(@PathVariable Long id){
        return new ResponseEntity(new ReceiptDTO(receiptService.getReceiptById(id)), HttpStatus.OK);
    }



}
