package com.example.dao.productLines;

import com.example.Excercise1.entities.Productlines;
import com.example.Excercise1.exceptions.GetDataException;
import com.example.Excercise1.exceptions.SetDataException;
import com.example.Excercise1.repository.Dao;
import com.example.Excercise1.repository.PreparedStatementDao;
import com.example.Excercise1.utils.ErrorCodeMap;
import com.example.exceptions.DataAccessFailureException;
import com.example.exceptions.DataNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductLinesDaoImpl implements ProductLinesDao {

    private static final String GET_PRODUCTLINES;
    private static final String GET_SINGLE_PRODUCT_LINE;

    static {
        GET_PRODUCTLINES = "select * from " + ErrorCodeMap.PRODUCTLINES_TABLE;
        GET_SINGLE_PRODUCT_LINE = "select * from " + ErrorCodeMap.PRODUCTLINES_TABLE + " where " + ErrorCodeMap.PRODUCTS_PRODUCTLINE + " = ?";
    }

    private static final Logger log = LogManager.getLogger(ProductLinesDaoImpl.class);
    private final PreparedStatementDao preparedStatementDao;
    private final Dao dao;

    @Autowired
    public ProductLinesDaoImpl(PreparedStatementDao preparedStatementDao, Dao dao) {
        this.preparedStatementDao = preparedStatementDao;
        this.dao = dao;
    }

    @Override
    public List<Productlines> getAllProductLines() {
        try {
            return this.dao.getValueObjects(GET_PRODUCTLINES, Productlines.class);
        } catch (GetDataException e) {
            throw new DataNotFoundException("");
        }
    }

    @Override
    public void saveProductLine(Productlines productlines) {
        try {
            this.preparedStatementDao.setValueObject(productlines);
        } catch (SetDataException e) {
            throw new DataAccessFailureException("");
        }
    }

    @Override
    public Productlines getSingProductLine(String productline) {
        try {
            return this.preparedStatementDao.getSingleValueObject(GET_SINGLE_PRODUCT_LINE, Collections.singletonList(productline), Productlines.class);
        } catch (GetDataException e) {
            throw new DataAccessFailureException("");
        }
    }
}
