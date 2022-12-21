package com.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
public class ProductsEntity {
    private String productCode;
    private String productName;
    private String productLine;
    private String productScale;
    private String productVendor;
    private String productDescription;
    private short quantityInStock;
    private BigDecimal buyPrice;
    private BigDecimal msrp;
}
