package com.example.dao.offices;

import com.example.Excercise1.entities.Offices;
import com.example.Excercise1.repository.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class OfficeDaoImpl implements OfficeDao{
    private final Dao dao;

    @Autowired
    public OfficeDaoImpl(Dao dao) {
        this.dao = dao;
    }

    private static final String sql = "select * from offices";
    private static final String MAX_OFFICE_CODE = "SELECT * FROM offices ORDER BY officeCode DESC LIMIT 1;";
    private static final String GET_OFFICE_BY_OFFICE_CODE = "SELECT * FROM offices where officeCode = ?";
    @Override
    public List<Offices> getAllOffice() {
        return dao.getListOfValueObject(sql, Offices.class);
    }

    @Override
    public void saveListOfOffice(Offices offices) {
        try {
            dao.setValueObject(offices);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getMaxOfficeCode() {
        try {
            Offices object = this.dao.getSingleValueObject(MAX_OFFICE_CODE, Offices.class);
            return Integer.parseInt(object.getOfficeCode());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Offices getOfficesByCodeId(int officeCodeId) {
        try {
            return this.dao.getSingleValueObjectWithPreparedStatement(GET_OFFICE_BY_OFFICE_CODE, Collections.singletonList(officeCodeId), Offices.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
