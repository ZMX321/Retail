package com.example.retail.service.impl;


import com.example.retail.pojo.Customer;
import com.example.retail.pojo.Order;
import com.example.retail.repository.CustomerOrderRepository;
import com.example.retail.repository.CustomerRepository;
import com.example.retail.repository.OrderRepository;
import com.example.retail.service.RetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetailServiceImpl implements RetailService {


    private CustomerRepository customerRepository;

    private OrderRepository orderRepository;

    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    public RetailServiceImpl(CustomerRepository customerRepository, OrderRepository orderRepository, CustomerOrderRepository customerOrderRepository){
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.customerOrderRepository = customerOrderRepository;
    }


    @Override
    public Integer insertCustomer(Customer customer) {
        customerRepository.save(customer);
        return customer.getId();
    }

    @Override
    public void insertOrder(Integer id, Order order) {
        calReward(order);
        orderRepository.save(order);
        Integer orderId = order.getId();

        customerOrderRepository.insertCusOrd(id, orderId);
    }

    @Override
    public void getRewardSummary(Integer id) {

    }

    private void calReward(Order order){
        Integer amount = order.getAmount();
        int reward = 0;

        if(amount > 100){
            reward += 2 * (amount - 100);
        }else if(amount > 50){
            reward += (amount - 50);
        }

        order.setReward(reward);
    }


}
