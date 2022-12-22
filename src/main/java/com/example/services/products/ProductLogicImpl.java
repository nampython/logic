package com.example.services.products;

import com.example.Excercise1.entities.Products;
import com.example.Excercise1.utils.ErrorCodeMap;
import com.example.dao.products.ProductDao;
import com.example.domain.productdomain.ProductsEntity;
import com.example.domain.productdomain.ProductsEntityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductLogicImpl implements ProductLogic {

    private final ProductDao productDao;
    @Autowired
    public ProductLogicImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    /**
     *
     * @return
     */
    @Override
    public ProductsEntityConfig getAllProductEntities() {
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
            productsEntity.setProductCode(product.getProductCode());
            productsEntity.setProductName(product.getProductName());
            productsEntity.setProductLine(product.getProductLine());
            productsEntity.setProductScale(product.getProductScale());
            productsEntity.setProductVendor(product.getProductVendor());
            productsEntity.setProductDescription(product.getProductDescription());
            productsEntity.setQuantityInStock(product.getQuantityInStock());
            productsEntity.setBuyPrice(product.getBuyPrice());
            productsEntity.setMsrp(product.getMsrp());
            productsEntity.setResultCode(ErrorCodeMap.RECORD_FOUND);
            productsEntities.add(productsEntity);
        }
    }
}
