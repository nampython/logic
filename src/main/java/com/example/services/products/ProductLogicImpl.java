package com.example.services.products;

import com.example.Excercise1.entities.Productlines;
import com.example.Excercise1.entities.Products;
import com.example.Excercise1.utils.ErrorCodeMap;
import com.example.Excercise1.utils.LogicEntity;
import com.example.dao.productLines.ProductLinesDao;
import com.example.dao.products.ProductDao;
import com.example.domain.productdomain.ProductWithLines;
import com.example.domain.productdomain.ProductWithLinesConfig;
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
    private final ProductLinesDao productLinesDao;
    private final LogicEntity logicEntity;
    @Autowired
    public ProductLogicImpl(ProductDao productDao, ProductLinesDao productLinesDao, LogicEntity logicEntity) {
        this.productDao = productDao;
        this.productLinesDao = productLinesDao;
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
     * @return
     */
    @Override
    public ProductWithLinesConfig getProductWithLines() {
        ProductWithLinesConfig productWithLinesConfig = new ProductWithLinesConfig();
        List<ProductWithLines> productWithLines = new ArrayList<>();
        productWithLinesConfig.setProductWithLines(productWithLines);
        List<Products> allProducts = this.productDao.getAllProducts();

        for (Products product : allProducts) {
            String productLine = product.getProductLine();
            Productlines productlines = this.productLinesDao.getSingProductLine(productLine);
            this.mergeProductWithLine(productWithLines, product, productlines);
        }
        return productWithLinesConfig;
    }

    private void mergeProductWithLine(List<ProductWithLines> productWithLines, Products product, Productlines productlines) {
        ProductWithLines productWithLine = new ProductWithLines();
        this.logicEntity.setValue(product, productWithLine, Arrays.asList(
                "productCode","productName","productScale","productVendor","productDescription","quantityInStock","buyPrice","msrp",
                "productLine"
        ));
        this.logicEntity.setValue(productlines, productWithLine, Arrays.asList("textDescription","htmlDescription","image"));
        productWithLines.add(productWithLine);
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
