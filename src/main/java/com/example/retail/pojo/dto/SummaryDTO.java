package com.example.retail.pojo.dto;

import com.example.retail.pojo.Customer;
import com.example.retail.pojo.ISummary;
import com.example.retail.pojo.Summary;
import lombok.Data;

import java.util.List;

@Data
public class SummaryDTO {

    private String firstName;

    private String lastName;

    private List<Summary> rewardSummary;

    private Integer totalReward;

    public SummaryDTO(Customer customer, List<Summary> list){
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.rewardSummary = list;
        this.totalReward = calTotalReward(list);

    }

    private Integer calTotalReward(List<Summary> list) {
        return list.stream().map(s -> Integer.valueOf(s.getReward())).reduce(0, Integer::sum);
    }

    @Override
    public String toString() {
        return "SummaryDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", rewardSummary=" + rewardSummary +
                ", totalReward=" + totalReward +
                '}';
    }
}
