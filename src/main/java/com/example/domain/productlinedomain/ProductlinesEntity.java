package com.example.domain.productlinedomain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductlinesEntity {
    private String productLine;
    private String textDescription;
    private String htmlDescription;
    private String image;
    private int resultCode;
    private boolean deleted;
    private boolean dirty;
}
