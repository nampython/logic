package com.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
public class OrderdetailsEntity {
    private int orderNumber;
    private String productCode;
    private int quantityOrdered;
    private BigDecimal priceEach;
    private short orderLineNumber;
}
