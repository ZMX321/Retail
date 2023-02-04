package com.example.retail.service.impl;

import com.example.retail.pojo.Customer;
import com.example.retail.pojo.ISummary;
import com.example.retail.pojo.Receipt;
import com.example.retail.pojo.Summary;
import com.example.retail.pojo.dto.CustomerDTO;
import com.example.retail.pojo.dto.SummaryDTO;
import com.example.retail.repository.CustomerRepository;
import com.example.retail.repository.ReceiptRepository;
import com.example.retail.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final ReceiptRepository receiptRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ReceiptRepository receiptRepository) {
        this.customerRepository = customerRepository;
        this.receiptRepository = receiptRepository;
    }

    @Override
    public Customer getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            return customer.get();
        } else{
            throw new IllegalArgumentException("Cannot find the customer.");
        }
    }

    @Override
    @Transactional
    public Long insertReceiptForCustomer(Long id, Receipt receipt) {
        Customer c = getCustomerById(id);
        receipt.setCustomer(c);
        receipt.setCreateDate(new Date());
        receipt.setReward(calReward(receipt.getAmount()));
        c.getReceiptList().add(receipt);

        receiptRepository.save(receipt);

        return receipt.getId();
    }

    private Integer calReward(Integer amount){
        int reward = 0;
        if(amount > 100){
            reward += 2 * (amount - 100) + 50;

        }else if(amount > 50){
            reward += (amount - 50);
        }
        return reward;
    }

    @Override
    public SummaryDTO getSummary(Long id, String start, String end){
        return new SummaryDTO(getCustomerById(id), getRewardSummary(id, start, end));
    }

    public List<Summary> getRewardSummary(Long id, String start, String end) {
        if(start.compareTo(end) < 0){
            throw new IllegalArgumentException("Invalid date input");
        }
        String startMonth;
        String endMonth;

        if(start.equals("null") || end.equals("null")){
            startMonth = getPrevTwoMonthTime();
            endMonth = getCurMonth();
        }else{
            startMonth = start;
            endMonth = end;
        }


        log.info("Input start month: " + startMonth);
        log.info("Input end month: " + endMonth);

        return receiptRepository.getRewardSummary(id).stream()
                                                    .filter(s -> startMonth.compareTo(s.getDate()) <= 0 && s.getDate().compareTo(endMonth) <= 0)
                                                    .sorted(Comparator.comparing(ISummary::getDate).reversed())
                                                    .map(Summary::new)
                                                    .collect(Collectors.toList());
    }

    private String getCurMonth(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        return cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH);
    }

    private String getPrevTwoMonthTime(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -2);

        return cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH);
    }



    @Override
    @Transactional
    public Long insertCustomer(Customer customer) {
        customerRepository.save(customer);
        return customer.getId();
    }



}
