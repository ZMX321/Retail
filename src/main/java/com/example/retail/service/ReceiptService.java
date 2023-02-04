package com.example.retail.service;

import com.example.retail.pojo.Receipt;
import org.springframework.stereotype.Service;

@Service
public interface ReceiptService {


    Receipt getReceiptById(Long id);
}
