package com.example.retail.pojo.dto;

import com.example.retail.pojo.Receipt;
import lombok.Data;

import java.util.Date;

@Data
public class ReceiptDTO {

    private Integer amount;

    private Integer reward;

    private Date createDate;

    public ReceiptDTO(Receipt receipt){
        this.amount = receipt.getAmount();
        this.reward = receipt.getReward();
        this.createDate = receipt.getCreateDate();
    }

}
