package com.example.retail.service.impl;


import com.example.retail.pojo.Customer;
import com.example.retail.pojo.Receipt;
import com.example.retail.repository.CustomerRepository;
import com.example.retail.repository.ReceiptRepository;
import com.example.retail.service.ReceiptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ReceiptServiceImpl implements ReceiptService {


    private ReceiptRepository receiptRepository;


    @Autowired
    public ReceiptServiceImpl(ReceiptRepository receiptRepository){
        this.receiptRepository = receiptRepository;
    }

    @Override
    public Receipt getReceiptById(Long id) {
        Optional<Receipt> receipt = receiptRepository.findById(id);
        if(receipt.isPresent()){
            return receipt.get();
        } else{
            throw new IllegalArgumentException("Cannot find the receipt.");
        }
    }


}
