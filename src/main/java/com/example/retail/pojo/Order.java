package com.example.retail.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "order")
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer amount;

    @Column
    private Integer reward;

    @Column
    private Date createDate;

    @OneToMany(mappedBy = "o", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Customer_Order> customer_orderList;

    public List<Customer_Order> getCustomer_orderList() {
        return customer_orderList;
    }

    public void setCustomer_orderList(List<Customer_Order> customer_orderList) {
        this.customer_orderList = customer_orderList;
    }
}
