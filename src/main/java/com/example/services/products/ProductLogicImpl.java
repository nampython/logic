package com.example.services.products;

import com.example.Excercise1.entities.Products;
import com.example.Excercise1.utils.ErrorCodeMap;
import com.example.Excercise1.utils.LogicEntity;
import com.example.dao.products.ProductDao;
import com.example.domain.productdomain.ProductsEntity;
import com.example.domain.productdomain.ProductsEntityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductLogicImpl implements ProductLogic {

    private final ProductDao productDao;
    private final LogicEntity logicEntity;
    @Autowired
    public ProductLogicImpl(ProductDao productDao, LogicEntity logicEntity) {
        this.productDao = productDao;
        this.logicEntity = logicEntity;
    }

    /**
     *
     * @return
     */
    @Override
    public ProductsEntityConfig getAllProductEntities()  {
        ProductsEntityConfig productsEntityConfig = new ProductsEntityConfig();
        List<Products> allProducts = productDao.getAllProducts();
        List<ProductsEntity> productsEntities = new ArrayList<>();
        productsEntityConfig.setProductsEntities(productsEntities);
        this.mergeProductEntities(allProducts, productsEntities);
        return productsEntityConfig;
    }

    /**
     *
     * @param allProducts
     * @param productsEntities
     */
    private void mergeProductEntities(List<Products> allProducts, List<ProductsEntity> productsEntities) {
        for (Products product : allProducts) {
            ProductsEntity productsEntity = new ProductsEntity();
            logicEntity.setValue(product, productsEntity,
                    Arrays.asList("productCode", "productName", "productLine",
                    "productScale", "productVendor", "productDescription", "quantityInStock", "buyPrice", "msrp")
            );
            productsEntity.setResultCode(ErrorCodeMap.RECORD_FOUND);
            productsEntities.add(productsEntity);
        }
    }
}
