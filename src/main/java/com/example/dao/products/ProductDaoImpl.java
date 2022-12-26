package com.example.dao.products;

import com.example.Excercise1.entities.Products;
import com.example.Excercise1.exceptions.GetDataException;
import com.example.Excercise1.repository.Dao;
import com.example.dao.productLines.ProductLinesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {
    private final Dao dao;
    public static final String GET_ALL_PRODUCTS = "select * from products";

    @Autowired
    public ProductDaoImpl(Dao dao) {
        this.dao = dao;
    }

    @Override
    public List<Products> getAllProducts() {
        try {
            return dao.getListOfValueObject(GET_ALL_PRODUCTS, Products.class);
        } catch (GetDataException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveProduct() {

    }
}
