package com.example.retail.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    public List<Customer_Order> getCustomer_orderList() {
        return customer_orderList;
    }

    public void setCustomer_orderList(List<Customer_Order> customer_orderList) {
        this.customer_orderList = customer_orderList;
    }

    @OneToMany(mappedBy = "c", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Customer_Order> customer_orderList;

}
