package com.example.retail.repository;

import com.example.retail.pojo.ISummary;
import com.example.retail.pojo.Receipt;
import net.bytebuddy.build.Plugin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long>{

    @Query(value = "SELECT SUM(AMOUNT) as amount, SUM(REWARD) as reward, SUBSTRING(CREATE_DATE, 0 , 7) as date FROM RECEIPT GROUP BY SUBSTRING(CREATE_DATE, 0 , 7), CID HAVING CID = ?1", nativeQuery = true)
    List<ISummary> getRewardSummary(Long id);
}
