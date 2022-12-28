package com.example.dao.employees;

import com.example.Excercise1.repository.Dao;
import com.example.Excercise1.utils.ErrorCodeMap;
import com.example.Excercise1.valueObject.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    private final Dao dao;

    @Autowired
    public EmployeeDaoImpl(Dao dao) {
        this.dao = dao;
    }

    private final static String GET_EMPLOYEE_CUSTOMER_PAYMENTS;

    static {
        GET_EMPLOYEE_CUSTOMER_PAYMENTS = "SELECT " + " concat(" + ErrorCodeMap.EMPLOYEES_LASTNAME + ",' '," + ErrorCodeMap.EMPLOYEES_FIRSTNAME + ") " + " as fullname" + ", " +
                ErrorCodeMap.CUSTOMERS_CUSTOMER_NAME + ", " +
                ErrorCodeMap.PAYMENTS_CHECKNUMBER + ", " +
                ErrorCodeMap.PAYMENTS_AMOUNT + " " +
                "from " + ErrorCodeMap.EMPLOYEES_TABLE + " left join " + ErrorCodeMap.CUSTOMERS_TABLE + " on " + ErrorCodeMap.EMPLOYEES_EMPLOYEENUMBER + " = " +
                ErrorCodeMap.CUSTOMERS_SALES_REP_EMPLOYEE_NUMBER + " left join " + ErrorCodeMap.PAYMENTS_TABLE + " on " + ErrorCodeMap.PAYMENTS_TABLE + "." +
                ErrorCodeMap.CUSTOMERS_CUSTOMER_NUMBER + " = " + ErrorCodeMap.CUSTOMERS_TABLE + "." + ErrorCodeMap.CUSTOMERS_CUSTOMER_NUMBER +
                " order by " + ErrorCodeMap.CUSTOMERS_CUSTOMER_NAME + ", " + ErrorCodeMap.PAYMENTS_CHECKNUMBER;
    }

    @Override
    public List<List<Value>> getEmployeesWithCustomerAndPayment() {
        try {
            return dao.getMultipleRows(GET_EMPLOYEE_CUSTOMER_PAYMENTS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
