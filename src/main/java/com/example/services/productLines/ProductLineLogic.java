package com.example.services.productLines;

import com.example.domain.productlinedomain.ProductLineConfig;

public interface ProductLineLogic {
    public abstract ProductLineConfig getAllProductLines();
    public abstract ProductLineConfig saveProductLines(ProductLineConfig productLineConfig);
}
