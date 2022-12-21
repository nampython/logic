package com.example.services.offices;

import com.example.Excercise1.entities.Offices;
import com.example.Excercise1.utils.ErrorCode;
import com.example.Excercise1.utils.ErrorCodeMap;
import com.example.dao.offices.OfficeDao;
import com.example.domain.officedomain.OfficesEntity;
import com.example.domain.officedomain.OfficeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfficeLogicImpl implements OfficeLogic {

    private final OfficeDao officeDao;

    @Autowired
    public OfficeLogicImpl(OfficeDao officeDao) {
        this.officeDao = officeDao;
    }

    /**
     *
     * @return
     */
    @Override
    public OfficeConfig getAllOffice() {
        OfficeConfig officeConfig = new OfficeConfig();
        List<OfficesEntity> officesEntityArrayList = new ArrayList<>();
        officeConfig.setOfficesEntityList(officesEntityArrayList);
        List<Offices> offices = officeDao.getAllOffice();

        for (Offices office : offices) {
            OfficesEntity officesEntity = this.mergedOfficeToEntity(office);
            officesEntityArrayList.add(officesEntity);
        }
        return officeConfig;
    }

    /**
     *
     * @param offices
     * @return
     */
    private OfficesEntity mergedOfficeToEntity(Offices offices) {
        OfficesEntity officesEntity = new OfficesEntity();
        officesEntity.setOfficeCode(offices.getOfficeCode());
        officesEntity.setCity(offices.getCity());
        officesEntity.setPhone(offices.getPhone());
        officesEntity.setAddressLine1(offices.getAddressLine1());
        officesEntity.setAddressLine2(offices.getAddressLine2());
        officesEntity.setState(offices.getState());
        officesEntity.setCountry(offices.getCountry());
        officesEntity.setCountry(offices.getCountry());
        officesEntity.setPostalCode(offices.getPostalCode());
        officesEntity.setTerritory(offices.getTerritory());
        officesEntity.setResultCode(ErrorCodeMap.RECORD_FOUND);
        return officesEntity;
    }

    /**
     *
     * @param officeCodeId
     * @return
     */
    @Override
    public OfficesEntity getOfficeByOfficeCode(int officeCodeId) {
        Offices offices = this.officeDao.getOfficesByCodeId(officeCodeId);
        return this.mergedOfficeToEntity(offices);
    }

    /**
     *
     * @param officeConfig
     * @return
     */
    @Override
    public OfficeConfig saveListOfOffice(OfficeConfig officeConfig) {
        ErrorCode errorCode = new ErrorCode();
        officeConfig.setErrorCode(errorCode);
        List<Offices> offices = mergedOfficeEntities(officeConfig);
        if (errorCode.getNumErrors() == 0) {
            // after validating, we save data here
            for (Offices office : offices) {
                officeDao.saveListOfOffice(office);
            }
        }
        return officeConfig;
    }

    /**
     *
     * @param officeConfig
     * @return
     */
    private List<Offices> mergedOfficeEntities(OfficeConfig officeConfig) {
        List<Offices> offices = new ArrayList<>();
        List<OfficesEntity> officesEntityList = officeConfig.getOfficesEntityList();
        ErrorCode errorCode = officeConfig.getErrorCode();

        for (int i = 0; i < officesEntityList.size(); i++) {
            OfficesEntity officesEntity = officesEntityList.get(i);
            // Validate single officesEntity
            this.validateOfficeConfig(officesEntity, errorCode, i + 1);
            if (errorCode.getNumErrors() == 0) {
                Offices office = new Offices();
                // new record
                if (officesEntity.getResultCode() == ErrorCodeMap.NEW_RECORD) {
                    office.setOfficeCode(String.valueOf(this.officeDao.getMaxOfficeCode() + 1));
                // delete record
                } else if ((officesEntity.isDeleted()) || (office.getResultCode() == ErrorCodeMap.DELETED_RECORD)) {
                    office.setResultCode(ErrorCodeMap.DELETED_RECORD);
                    office.setOfficeCode(officesEntity.getOfficeCode());
                // found record
                } else {
                    office.setOfficeCode(officesEntity.getOfficeCode());
                    office.setResultCode(officesEntity.getResultCode());
                    office.setDirty(officesEntity.isDirty());
                }
                office.setCity(officesEntity.getCity());
                office.setPhone(officesEntity.getPhone());
                office.setAddressLine1(officesEntity.getAddressLine1());
                office.setAddressLine2(officesEntity.getAddressLine2());
                office.setState(officesEntity.getState());
                office.setPostalCode(officesEntity.getPostalCode());
                office.setTerritory(officesEntity.getTerritory());
                office.setCountry(officesEntity.getCountry());
                offices.add(office);
            }
        }
        return offices;
    }

    /**
     *
     * @param officesEntity
     * @param errorCode
     * @param i
     */
    private void validateOfficeConfig(OfficesEntity officesEntity, ErrorCode errorCode, int i) {
        String city = officesEntity.getCity();
        String phoneNumber = officesEntity.getPhone();
        String postalCode = officesEntity.getPostalCode();
        if (city.equals("")) {
            errorCode.addErrorMsg(i, "City is not empty");
        }
        if (phoneNumber.matches("[a-zA-Z]+")) {
            errorCode.addErrorMsg(i, "Phone is only number");
        }
        if (postalCode.matches("[a-zA-Z]+")) {
            errorCode.addErrorMsg(i, "PostalCode is only number");
        }
    }
}
