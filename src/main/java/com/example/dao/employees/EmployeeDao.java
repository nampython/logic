package com.example.dao.employees;

import com.example.Excercise1.valueObject.Value;

import java.util.List;

public interface EmployeeDao {
    public abstract List<List<Value>> getEmployeesWithCustomerAndPayment();
}
