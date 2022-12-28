package com.example.dao.orders;

import com.example.Excercise1.exceptions.GetDataException;
import com.example.Excercise1.repository.Dao;
import com.example.Excercise1.repository.PreparedStatementDao;
import com.example.Excercise1.utils.ErrorCodeMap;
import com.example.Excercise1.valueObject.Value;
import com.example.exceptions.DataAccessFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    private final Dao dao;
    private final PreparedStatementDao preparedStatementDao;

    @Autowired
    public OrderDaoImpl(Dao dao, PreparedStatementDao preparedStatementDao) {
        this.dao = dao;
        this.preparedStatementDao = preparedStatementDao;
    }

    private final static String GET_ORDER_CUSTOMERS;
    private final static String GET_TOTAL_ORDER_BY_YEAR;

    static {
        GET_ORDER_CUSTOMERS = "select " +
                ErrorCodeMap.ORDERS_ORDERNUMBER + ", " + ErrorCodeMap.ORDER_DETAILS_ORDERLINENUMBER + ", " + ErrorCodeMap.CUSTOMERS_CUSTOMER_NAME + ", " +
                ErrorCodeMap.PRODUCTS_PRODUCTNAME + ", " + ErrorCodeMap.PRODUCTLINES_PRODUCTLINE + ", " + ErrorCodeMap.ORDERS_STATUS + ", " +
                ErrorCodeMap.ORDER_DETAILS_QUANTITYORDERED + ", " + ErrorCodeMap.ORDER_DETAILS_PRICEEACH +
                " from " + ErrorCodeMap.ORDERS_TABLE + " " +
                " inner join " + ErrorCodeMap.ORDER_DETAILS_TABLE + " using(" + ErrorCodeMap.ORDERS_ORDERNUMBER + ")" +
                " inner join " + ErrorCodeMap.PRODUCTS_TABLE + " using(" + ErrorCodeMap.PRODUCTS_PRODUCTCODE + ")" +
                " inner join " + ErrorCodeMap.CUSTOMERS_TABLE + " using(" + ErrorCodeMap.CUSTOMERS_CUSTOMER_NUMBER + ")" +
                " order by " + ErrorCodeMap.ORDERS_ORDERNUMBER + ", " + ErrorCodeMap.ORDER_DETAILS_ORDERLINENUMBER + ";";
        GET_TOTAL_ORDER_BY_YEAR = "select year(" + ErrorCodeMap.ORDERS_ORDERDATE + ") as year, sum(" +
                ErrorCodeMap.ORDER_DETAILS_QUANTITYORDERED + " * " + ErrorCodeMap.ORDER_DETAILS_PRICEEACH + ") as total " +
                " from " + ErrorCodeMap.ORDERS_TABLE + " inner join " + ErrorCodeMap.ORDER_DETAILS_TABLE + " using(" + ErrorCodeMap.ORDER_DETAILS_ORDERNUMBER + ") " +
                " where status = ? " +
                " group by year" +
                " having year = ?;";
    }

    @Override
    public List<List<Value>> getOrdersDetailsCustomer() {
        try {
            return dao.getMultipleRows(GET_ORDER_CUSTOMERS);
        } catch (GetDataException e) {
            throw new DataAccessFailureException("");
        }
    }

    @Override
    public List<List<Value>> getTotalOrderEachYear(List<Object> params) {
        try {
            return preparedStatementDao.getValueObjects(GET_TOTAL_ORDER_BY_YEAR, params);
        } catch (GetDataException e) {
            throw new DataAccessFailureException("");
        }
    }
}
