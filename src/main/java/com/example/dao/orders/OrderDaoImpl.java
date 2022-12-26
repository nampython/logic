package com.example.dao.orders;

import com.example.Excercise1.exceptions.GetDataException;
import com.example.Excercise1.repository.Dao;
import com.example.Excercise1.utils.ErrorCodeMap;
import com.example.Excercise1.valueObject.Value;
import com.example.exceptions.DataAccessFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao{
    private final static String GET_ORDER_CUSTOMERS;

    static {
        GET_ORDER_CUSTOMERS = "select " +
                ErrorCodeMap.ORDERS_ORDERNUMBER + ", " + ErrorCodeMap.ORDER_DETAILS_ORDERLINENUMBER + ", " + ErrorCodeMap.CUSTOMERS_CUSTOMER_NAME + ", " +
                ErrorCodeMap.PRODUCTS_PRODUCTNAME + ", " + ErrorCodeMap.PRODUCTLINES_PRODUCTLINE + ", " + ErrorCodeMap.ORDERS_STATUS + ", " +
                ErrorCodeMap.ORDER_DETAILS_QUANTITYORDERED + ", " + ErrorCodeMap.ORDER_DETAILS_PRICEEACH +
                " from " + ErrorCodeMap.ORDERS_TABLE + " " +
                " inner join " + ErrorCodeMap.ORDER_DETAILS_TABLE + " using(" + ErrorCodeMap.ORDERS_ORDERNUMBER + ")" +
                " inner join " +  ErrorCodeMap.PRODUCTS_TABLE + " using(" + ErrorCodeMap.PRODUCTS_PRODUCTCODE +  ")" +
                " inner join " + ErrorCodeMap.CUSTOMERS_TABLE + " using(" + ErrorCodeMap.CUSTOMERS_CUSTOMER_NUMBER + ")" +
                " order by " + ErrorCodeMap.ORDERS_ORDERNUMBER + ", " + ErrorCodeMap.ORDER_DETAILS_ORDERLINENUMBER + ";";
    }

    private final Dao dao;
    @Autowired
    public OrderDaoImpl(Dao dao) {
        this.dao = dao;
    }

    @Override
    public List<List<Value>> getOrdersDetailsCustomer() {
        try {
            return dao.getMultipleRowsWithStatement(GET_ORDER_CUSTOMERS);
        } catch (GetDataException e) {
            throw new DataAccessFailureException("");
        }
    }
}
