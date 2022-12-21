package com.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;


@Getter
@Setter
@NoArgsConstructor
public class PaymentsEntity {
    private int customerNumber;
    private String checkNumber;
    private Date paymentDate;
    private BigDecimal amount;

}
