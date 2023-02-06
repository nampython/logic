package com.example.dao.offices;

import com.example.Excercise1.entities.Offices;
import com.example.Excercise1.exceptions.SetDataException;
import com.example.Excercise1.repository.Dao;
import com.example.Excercise1.repository.PreparedStatementDao;
import com.example.Excercise1.utils.ErrorCodeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class OfficeDaoImpl implements OfficeDao {
    private static final String GET_ALL_OFFICES;
    private static final String MAX_OFFICE_CODE;
    private static final String GET_OFFICE_BY_OFFICE_CODE;
    private static final String GET_OFFICE_BY_CITIES;

    static {
        GET_ALL_OFFICES = "select * from " + ErrorCodeMap.OFFICE_TABLE;
        MAX_OFFICE_CODE = "select * from " + ErrorCodeMap.OFFICE_TABLE + " order by " + ErrorCodeMap.OFFICE_OFFICE_CODE + " desc limit 1;";
        GET_OFFICE_BY_OFFICE_CODE = "select * from " + ErrorCodeMap.OFFICE_TABLE + " where " + ErrorCodeMap.OFFICE_OFFICE_CODE + " = ?";
        GET_OFFICE_BY_CITIES = "select * " + ErrorCodeMap.OFFICE_TABLE + " where " + ErrorCodeMap.OFFICE_TABLE + " in (?, ?, ?)";
    }
    private final Dao dao;
    private final PreparedStatementDao preparedStatementDao;
    @Autowired
    public OfficeDaoImpl(Dao dao, PreparedStatementDao preparedStatementDao) {
        this.dao = dao;
        this.preparedStatementDao = preparedStatementDao;
    }

    @Override
    public List<Offices> getAllOffice() {
        return dao.getValueObjects(GET_ALL_OFFICES, Offices.class);
    }

    @Override
    public void saveListOfOffice(Offices offices) {
        try {
            this.preparedStatementDao.setValueObject(offices);
        } catch (SetDataException e) {
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
            return this.preparedStatementDao.getSingleValueObject(GET_OFFICE_BY_OFFICE_CODE, Collections.singletonList(officeCodeId), Offices.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //TODO: Complete this function
    @Override
    public List<Offices> getOfficesByCity(List<String> cities) {
        return null;
    }
}
