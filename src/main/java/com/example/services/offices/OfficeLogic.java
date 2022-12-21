package com.example.services.offices;


import com.example.domain.officedomain.OfficeConfig;
import com.example.domain.officedomain.OfficesEntity;

public interface OfficeLogic {
        public abstract OfficeConfig getAllOffice();
        public abstract OfficesEntity getOfficeByOfficeCode(int officeCodeId);
        public abstract OfficeConfig saveListOfOffice(OfficeConfig officeConfig);

}
