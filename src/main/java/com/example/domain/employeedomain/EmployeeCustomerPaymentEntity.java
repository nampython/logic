package com.example.domain.employeedomain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeCustomerPaymentEntity {
    private String fullName;
    private String customerName;
    private String checkNumber;
    private BigDecimal amount;
    private int resultCode;
    private boolean deleted;
    private boolean dirty;
}
