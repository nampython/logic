package com.example.services.customers;

import com.example.Excercise1.entities.Customers;
import com.example.Excercise1.utils.ErrorCodeMap;
import com.example.Excercise1.utils.LogicEntity;
import com.example.dao.customers.CustomerDao;
import com.example.domain.customerdomain.CustomerConfig;
import com.example.domain.customerdomain.CustomersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomerLogicImpl implements CustomerLogic{
    private final CustomerDao customerDao;
    private final LogicEntity logicEntity;
    @Autowired
    public CustomerLogicImpl(CustomerDao customerDao, LogicEntity logicEntity) {
        this.customerDao = customerDao;
        this.logicEntity = logicEntity;
    }

    @Override
    public CustomerConfig getAllCustomers() {
        CustomerConfig customerConfig = new CustomerConfig();
        List<CustomersEntity> customersEntities = new ArrayList<>();
        customerConfig.setCustomersEntities(customersEntities);
        List<Customers> allCustomers = this.customerDao.getAllCustomers();
        this.mergedCustomerEntities(allCustomers, customersEntities);
        return customerConfig;
    }

    private void mergedCustomerEntities(List<Customers> customers, List<CustomersEntity> customersEntities) {
        for (Customers customer : customers) {
            CustomersEntity customersEntity = new CustomersEntity();
            this.logicEntity.setValue(customer, customersEntity, Arrays.asList(
                    "customerNumber", "customerName", "contactLastName", "contactFirstName", "phone", "addressLine1"
                    , "addressLine2", "city", "state", "postalCode", "country", "salesRepEmployeeNumber", "creditLimit"
            ));
            customersEntity.setResultCode(ErrorCodeMap.RECORD_FOUND);
            customersEntities.add(customersEntity);
        }
    }
}
