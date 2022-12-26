package com.example.domain.productdomain;

import com.example.Excercise1.utils.ErrorCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties
public class ProductWithLinesConfig {
    private List<ProductWithLines> productWithLines;
    @JsonIgnore
    private ErrorCode errorCode;
}
