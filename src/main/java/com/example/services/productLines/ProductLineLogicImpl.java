package com.example.services.productLines;

import com.example.Excercise1.entities.Productlines;
import com.example.Excercise1.utils.ErrorCodeMap;
import com.example.dao.productLines.ProductLinesDao;
import com.example.domain.productlinedomain.ProductLineConfig;
import com.example.domain.productlinedomain.ProductlinesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductLineLogicImpl implements ProductLineLogic{
    private final ProductLinesDao productLinesDao;

    @Autowired
    public ProductLineLogicImpl(ProductLinesDao productLinesDao) {
        this.productLinesDao = productLinesDao;
    }

    @Override
    public ProductLineConfig getAllProductLines() {
        ProductLineConfig productLineConfig = new ProductLineConfig();
        List<ProductlinesEntity> productsEntityList = new ArrayList<>();
        productLineConfig.setProductlinesEntityList(productsEntityList);
        List<Productlines> allProductLines = this.productLinesDao.getAllProductLines();
        // merge productlines to productLinesEntity
        for (Productlines productlines : allProductLines) {
           this.mergeProductLineEntity(productlines, productsEntityList);
        }
        return productLineConfig;
    }
    private void mergeProductLineEntity(Productlines productlines,  List<ProductlinesEntity> productsEntityList) {
        ProductlinesEntity productlinesEntity = new ProductlinesEntity();
        productlinesEntity.setProductLine(productlines.getProductLine());
        productlinesEntity.setImage(productlines.getImage());
        productlinesEntity.setHtmlDescription(productlines.getHtmlDescription());
        productlinesEntity.setTextDescription(productlines.getTextDescription());
        productlinesEntity.setDeleted(false);
        productlinesEntity.setDirty(false);
        productlinesEntity.setResultCode(ErrorCodeMap.RECORD_FOUND);
        productsEntityList.add(productlinesEntity);
    }
}
