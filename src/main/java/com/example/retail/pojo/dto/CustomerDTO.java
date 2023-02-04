package com.example.retail.pojo.dto;

import com.example.retail.pojo.Customer;
import lombok.Data;

@Data
public class CustomerDTO {

    private String firstName;

    private String lastName;

    public CustomerDTO(Customer customer){
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
    }

}
