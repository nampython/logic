package com.example.domain.orderdomain;

import com.example.Excercise1.utils.ErrorCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties
public class OrderTotalYearConfig {
    List<OrderTotalYearEntity> orderTotalYearEntities;
    @JsonIgnore
    private ErrorCode errorCode;
}
