package com.example.domain.orderdomain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class OrderDetailsCustomer {
    private Integer orderNumber;
    private Short orderLineNumber;
    private String customerName;
    private String productName;
    private String productLine;
    private String status;
    private Integer quantityOrdered;
    private BigDecimal priceEach;
    private int resultCode;
    private boolean deleted;
    private boolean dirty;
}
