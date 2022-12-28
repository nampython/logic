package com.example.dao.orders;

import com.example.Excercise1.valueObject.Value;

import java.util.List;

public interface OrderDao {
    public abstract List<List<Value>> getOrdersDetailsCustomer();
    public abstract List<List<Value>> getTotalOrderEachYear(List<Object> params);
}
