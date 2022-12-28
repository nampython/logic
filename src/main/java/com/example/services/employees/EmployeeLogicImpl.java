package com.example.services.employees;

import com.example.Excercise1.valueObject.Value;
import com.example.dao.employees.EmployeeDao;
import com.example.domain.employeedomain.EmployeeCustomerPaymentConfig;
import com.example.domain.employeedomain.EmployeeCustomerPaymentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EmployeeLogicImpl implements EmployeeLogic{

    private final EmployeeDao employeeDao;
    @Autowired
    public EmployeeLogicImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    /**
     *
     * @return
     */
    @Override
    public EmployeeCustomerPaymentConfig getCustomerPaymentConfig() {
        EmployeeCustomerPaymentConfig employeeCustomerPaymentConfig = new EmployeeCustomerPaymentConfig();
        List<EmployeeCustomerPaymentEntity> employeeCustomerPaymentEntities = new ArrayList<>();
        employeeCustomerPaymentConfig.setEmployeeCustomerPaymentEntities(employeeCustomerPaymentEntities);
        this.getEmployeeCustomerPaymentList(employeeCustomerPaymentEntities);
        return employeeCustomerPaymentConfig;
    }

    /**
     *
     * @param employeeCustomerPaymentEntities
     */
    private void getEmployeeCustomerPaymentList(List<EmployeeCustomerPaymentEntity> employeeCustomerPaymentEntities) {
        List<List<Value>> employeesWithCustomerAndPayments = this.employeeDao.getEmployeesWithCustomerAndPayment();

        Iterator<List<Value>> iterator = employeesWithCustomerAndPayments.iterator();
        while (iterator.hasNext()) {
            List<Value> values = iterator.next();
            EmployeeCustomerPaymentEntity employeeCustomerPaymentEntity = new EmployeeCustomerPaymentEntity();
            employeeCustomerPaymentEntity.setFullName(values.get(0).toString());
            employeeCustomerPaymentEntity.setCustomerName(values.get(1).toString());
            employeeCustomerPaymentEntity.setCheckNumber(values.get(2).toString());
            employeeCustomerPaymentEntity.setAmount(values.get(3).toBigDecimal());
            employeeCustomerPaymentEntities.add(employeeCustomerPaymentEntity);
        }
    }
}
