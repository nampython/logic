package com.example.domain.productlinedomain;

import com.example.Excercise1.utils.ErrorCode;
import com.example.domain.officedomain.OfficesEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties
@Getter
@Setter
public class ProductLineConfig {
    private List<ProductlinesEntity> productlinesEntityList;
    @JsonIgnore
    private ErrorCode errorCode;
}
