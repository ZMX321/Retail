package com.example.retail.pojo;

import com.example.retail.pojo.dto.SummaryDTO;
import lombok.Data;

@Data
public class Summary {

    private String monthIdentifier;

    private String amount;

    private String reward;

    public Summary(ISummary s){
        this.monthIdentifier = s.getDate();
        this.amount = s.getAmount();
        this.reward = s.getReward();
    }
}
