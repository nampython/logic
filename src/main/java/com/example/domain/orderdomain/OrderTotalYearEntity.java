package com.example.domain.orderdomain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class OrderTotalYearEntity {
    private String year;
    private BigDecimal total;
}
