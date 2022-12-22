package com.example.dao.products;

import com.example.Excercise1.entities.Products;

import java.util.List;

public interface ProductDao {
    public abstract List<Products> getAllProducts();
    public abstract void saveProduct();
}
