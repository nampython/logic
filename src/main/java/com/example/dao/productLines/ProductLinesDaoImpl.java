package com.example.dao.productLines;

import com.example.Excercise1.entities.Offices;
import com.example.Excercise1.entities.Productlines;
import com.example.Excercise1.exceptions.GetDataException;
import com.example.Excercise1.repository.Dao;
import com.example.Excercise1.repository.DaoImpl;
import com.example.exceptions.DataAccessFailureException;
import com.example.exceptions.DataNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductLinesDaoImpl implements ProductLinesDao{

    private static final Logger log = LogManager.getLogger(ProductLinesDaoImpl.class);
    private final Dao dao;
    private static final String GET_PRODUCTLINES = "select * from productLines";

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
}
