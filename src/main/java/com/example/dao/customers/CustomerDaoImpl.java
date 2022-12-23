package com.example.dao.customers;

import com.example.Excercise1.entities.Customers;
import com.example.Excercise1.exceptions.GetDataException;
import com.example.Excercise1.repository.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao{
    private static final String GET_ALL_CUSTOMERS = "select * from customers";
    private final Dao dao;
    @Autowired
    public CustomerDaoImpl(Dao dao) {
        this.dao = dao;
    }

    @Override
    public List<Customers> getAllCustomers() {
        try {
            return dao.getListOfValueObject(GET_ALL_CUSTOMERS, Customers.class);
        } catch (GetDataException e) {
            throw new RuntimeException(e);
        }
    }
}
