package com.example.dao.productLines;

import com.example.Excercise1.entities.Productlines;
import com.example.Excercise1.exceptions.GetDataException;
import com.example.Excercise1.repository.Dao;
import com.example.exceptions.DataAccessFailureException;
import com.example.exceptions.DataNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductLinesDaoImpl implements ProductLinesDao {

    private static final Logger log = LogManager.getLogger(ProductLinesDaoImpl.class);
    private final Dao dao;
    private static final String GET_PRODUCTLINES = "select * from productLines";
    private static final String GET_SINGLE_PRODUCTLINE = "select * from productlines where productLine = ?";

    @Autowired
    public ProductLinesDaoImpl(Dao dao) {
        this.dao = dao;
    }

    @Override
    public List<Productlines> getAllProductLines() {
        try {
            return this.dao.getListOfValueObject(GET_PRODUCTLINES, Productlines.class);
        } catch (GetDataException e) {
            throw new DataNotFoundException("");
        }
    }

    @Override
    public void saveProductLine(Productlines productlines) {
        try {
            this.dao.setValueObject(productlines);
        } catch (SQLException e) {
            throw new DataAccessFailureException("");
        }
    }

    @Override
    public Productlines getSingProductLine(String productline) {
        try {
            return this.dao.getSingleValueObjectWithPreparedStatement(GET_SINGLE_PRODUCTLINE, Collections.singletonList(productline), Productlines.class);
        } catch (GetDataException e) {
            throw new DataAccessFailureException("");
        }
    }
}
