package com.example.retail.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customer_order")
@NoArgsConstructor
@AllArgsConstructor
public class Customer_Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "cid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer c;

    @JoinColumn(name = "oid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Order o;
}
