package com.example.domain.customerdomain;

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
public class CustomerConfig {
    private List<CustomersEntity> customersEntities;
    @JsonIgnore
    private ErrorCode errorCode;
}
