package com.example.dao.offices;

import com.example.Excercise1.entities.Offices;

import java.util.List;

public interface OfficeDao {
    public abstract List<Offices> getAllOffice();
    public abstract void saveListOfOffice(Offices offices);
    public abstract int getMaxOfficeCode();
    public abstract Offices getOfficesByCodeId(int officeCodeId);
}
