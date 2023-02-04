package com.example.retail.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "receipt")
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "cid")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @Column
    private Integer amount;

    @Column
    private Integer reward;

    @Column(name = "create_date")
    private Date createDate;

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", amount=" + amount +
                ", reward=" + reward +
                ", createDate=" + createDate +
                '}';
    }
}
