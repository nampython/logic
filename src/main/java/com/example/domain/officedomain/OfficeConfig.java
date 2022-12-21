package com.example.domain.officedomain;

import com.example.Excercise1.utils.ErrorCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@JsonIgnoreProperties
@Getter
@Setter
public class OfficeConfig {
    private List<OfficesEntity> officesEntityList;
    @JsonIgnore
    private ErrorCode errorCode;
}
