package com.example.services.products;

import com.example.domain.productdomain.ProductWithLinesConfig;
import com.example.domain.productdomain.ProductsEntityConfig;
import com.example.domain.productlinedomain.ProductLineConfig;

import java.lang.reflect.InvocationTargetException;

public interface ProductLogic {
    public abstract ProductsEntityConfig getAllProductEntities();
    public abstract ProductWithLinesConfig getProductWithLines();
}
