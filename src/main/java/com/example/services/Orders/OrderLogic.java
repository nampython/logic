package com.example.services.Orders;

import com.example.domain.orderdomain.OrderDetailsCustomerConfig;
import com.example.domain.orderdomain.OrderTotalYearConfig;

import java.util.List;

public interface OrderLogic {
    OrderDetailsCustomerConfig getOrderDetailsCustomer();
    OrderTotalYearConfig getOrderTotalYear(List<Object> params);
}
