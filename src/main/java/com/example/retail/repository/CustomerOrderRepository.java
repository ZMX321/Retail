package com.example.retail.repository;

import com.example.retail.pojo.Customer;
import com.example.retail.pojo.Customer_Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepository extends JpaRepository<Customer, Integer>{
    Integer insertCusOrd(Integer cId, Integer oId);
}
