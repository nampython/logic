package com.example.domain.productdomain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductWithLines {
    private String productCode;
    private String productName;
    private String productScale;
    private String productVendor;
    private String productDescription;
    private Short quantityInStock;
    private BigDecimal buyPrice;
    private BigDecimal msrp;
    private String productLine;
    private String textDescription;
    private String htmlDescription;
    private String image;
    private int resultCode;
    private boolean deleted;
    private boolean dirty;
}
