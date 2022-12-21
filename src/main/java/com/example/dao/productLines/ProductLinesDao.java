package com.example.dao.productLines;

import com.example.Excercise1.entities.Offices;
import com.example.Excercise1.entities.Productlines;

import java.util.List;

public interface ProductLinesDao {
    public abstract List<Productlines> getAllProductLines();
    public abstract void saveProductLine(Productlines productlines);
}
