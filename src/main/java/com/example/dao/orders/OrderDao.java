package com.example.dao.orders;

import com.example.Excercise1.valueObject.Value;

import java.util.List;

public interface OrderDao {
    public abstract List<List<Value>> getOrdersDetailsCustomer();
}
