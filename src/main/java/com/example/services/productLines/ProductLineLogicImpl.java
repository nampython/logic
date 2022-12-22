package com.example.services.productLines;

import com.example.Excercise1.entities.Productlines;
import com.example.Excercise1.utils.ErrorCode;
import com.example.Excercise1.utils.ErrorCodeMap;
import com.example.dao.productLines.ProductLinesDao;
import com.example.domain.productlinedomain.ProductLineConfig;
import com.example.domain.productlinedomain.ProductlinesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductLineLogicImpl implements ProductLineLogic{
    private final ProductLinesDao productLinesDao;

    @Autowired
    public ProductLineLogicImpl(ProductLinesDao productLinesDao) {
        this.productLinesDao = productLinesDao;
    }

    /**
     *
     * @return
     */
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

    /**
     *
     * @param productlines
     * @param productsEntityList
     */
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

    /**
     *
     * @param productLineConfig
     * @return
     */
    @Override
    public ProductLineConfig saveProductLines(ProductLineConfig productLineConfig) {
        ErrorCode errorCode = new ErrorCode();
        productLineConfig.setErrorCode(errorCode);
        List<Productlines> productlines = this.validateListOfProductlines(productLineConfig);

        if (errorCode.getNumErrors() == 0) {
            // save data here
            for (Productlines productline : productlines) {
                this.productLinesDao.saveProductLine(productline);
            }
        }
        return productLineConfig;
    }

    /**
     *
     * @param productLineConfig
     * @return
     */
    private List<Productlines> validateListOfProductlines(ProductLineConfig productLineConfig) {
        List<ProductlinesEntity> productlinesEntities = productLineConfig.getProductlinesEntityList();
        List<Productlines> productlines = new ArrayList<>();
        ErrorCode errorCode = productLineConfig.getErrorCode();

        for (int i = 0; i < productlinesEntities.size(); i++) {
            ProductlinesEntity productlinesEntity = productlinesEntities.get(i);
            this.checkEntity(i + 1, productlinesEntity, errorCode);
            if (errorCode.getNumErrors() == 0) {
                Productlines productline = new Productlines();
                if (productlinesEntity.getResultCode() == ErrorCodeMap.NEW_RECORD) {
                    productline.setResultCode(productlinesEntity.getResultCode());
                    productline.setProductLine(productlinesEntity.getProductLine());
                } else if (productlinesEntity.getResultCode() == ErrorCodeMap.DELETED_RECORD) {
                    productline.setResultCode(productlinesEntity.getResultCode());
                    productline.setProductLine(productlinesEntity.getProductLine());
                } else {
                    productline.setProductLine(productlinesEntity.getProductLine());
                    productline.setResultCode(productlinesEntity.getResultCode());
                    productline.setDirty(productlinesEntity.isDirty());
                }
                productline.setTextDescription(productlinesEntity.getTextDescription());
                productline.setHtmlDescription(productlinesEntity.getHtmlDescription());
                productline.setImage(productlinesEntity.getTextDescription());
                productlines.add(productline);
            }
        }
        return productlines;
    }

    private void checkEntity(int i, ProductlinesEntity productlinesEntity, ErrorCode errorCode) {
        List<Productlines> existProductLines = this.productLinesDao.getAllProductLines();
        Set<String> productLines = existProductLines.stream()
                .map(Productlines::getProductLine)
                .collect(Collectors.toSet());
        String productLineChecked = productlinesEntity.getProductLine();
        if (productLines.contains(productLineChecked) && (productlinesEntity.getResultCode() == ErrorCodeMap.NEW_RECORD)) {
            errorCode.addErrorMsg(i, "ProductLines has been exist in database, please use another name");
        }
    }
}
