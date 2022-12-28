package com.example.services.Orders;

import com.example.Excercise1.utils.ErrorCodeMap;
import com.example.Excercise1.valueObject.Value;
import com.example.dao.orders.OrderDao;
import com.example.domain.orderdomain.OrderDetailsCustomer;
import com.example.domain.orderdomain.OrderDetailsCustomerConfig;
import com.example.domain.orderdomain.OrderTotalYearConfig;
import com.example.domain.orderdomain.OrderTotalYearEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderLogicImpl implements OrderLogic {

    private final OrderDao orderDao;

    @Autowired
    public OrderLogicImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public OrderDetailsCustomerConfig getOrderDetailsCustomer() {
        OrderDetailsCustomerConfig orderDetailsCustomerConfig = new OrderDetailsCustomerConfig();
        List<OrderDetailsCustomer> orderDetailsCustomers = new ArrayList<>();
        orderDetailsCustomerConfig.setOrderDetailsCustomers(orderDetailsCustomers);
        this.getOrderDetailsCustomer(orderDetailsCustomers);
        return orderDetailsCustomerConfig;
    }

    @Override
    public OrderTotalYearConfig getOrderTotalYear(List<Object> params) {
        OrderTotalYearConfig orderTotalYearConfig = new OrderTotalYearConfig();
        List<OrderTotalYearEntity> orderTotalYearEntities = new ArrayList<>();
        orderTotalYearConfig.setOrderTotalYearEntities(orderTotalYearEntities);
        this.getOrderTotalYearEntities(orderTotalYearEntities, params);
        return orderTotalYearConfig;
    }

    private void getOrderTotalYearEntities(List<OrderTotalYearEntity> orderTotalYearEntities, List<Object> params) {
        List<List<Value>> totalOrderEachYear = this.orderDao.getTotalOrderEachYear(params);
        Iterator<List<Value>> iterator = totalOrderEachYear.iterator();

        while (iterator.hasNext()) {
            List<Value> values = iterator.next();
            OrderTotalYearEntity orderTotalYearEntity = new OrderTotalYearEntity();
            orderTotalYearEntity.setYear(values.get(0).toString());
            orderTotalYearEntity.setTotal(values.get(1).toBigDecimal());
            orderTotalYearEntities.add(orderTotalYearEntity);
        }
    }

    private void getOrderDetailsCustomer(List<OrderDetailsCustomer> orderDetailsCustomers) {
        List<List<Value>> ordersDetailsCustomer = this.orderDao.getOrdersDetailsCustomer();
        Iterator<List<Value>> iter = ordersDetailsCustomer.iterator();

        while (iter.hasNext()) {
            List<Value> values = iter.next();
            OrderDetailsCustomer orderDetailsCustomer = new OrderDetailsCustomer();
            orderDetailsCustomer.setOrderNumber(values.get(0).toInteger());
            orderDetailsCustomer.setOrderLineNumber(values.get(1).toShort());
            orderDetailsCustomer.setCustomerName(values.get(2).toString());
            orderDetailsCustomer.setProductName(values.get(3).toString());
            orderDetailsCustomer.setProductLine(values.get(4).toString());
            orderDetailsCustomer.setStatus(values.get(5).toString());
            orderDetailsCustomer.setQuantityOrdered(values.get(6).toInteger());
            orderDetailsCustomer.setPriceEach(values.get(7).toBigDecimal());
            orderDetailsCustomer.setResultCode(ErrorCodeMap.RECORD_FOUND);
            orderDetailsCustomers.add(orderDetailsCustomer);
        }
    }
}
