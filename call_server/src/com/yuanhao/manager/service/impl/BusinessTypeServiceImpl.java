package com.yuanhao.manager.service.impl;

import com.yuanhao.manager.dao.IBusinessTypeDao;
import com.yuanhao.manager.dao.entity.BusinessType;
import com.yuanhao.manager.dao.impl.BusinessTypeDaoImpl;
import com.yuanhao.manager.service.IBusinessTypeService;
import com.yuanhao.utils.Page;

import java.util.List;

public class BusinessTypeServiceImpl implements IBusinessTypeService {
    IBusinessTypeDao businessTypeDao = new BusinessTypeDaoImpl();
    @Override
    public int businessTypeAdd(BusinessType businessType) {
        return businessTypeDao.businessTypeAdd(businessType);
    }

    @Override
    public int businessTypeDelete(String... nos) {

        int rows = -1;
        for (int i = 0; i < nos.length; i++) {
            rows =  businessTypeDao.businessTypeDelete(nos[i]);
            if (rows < 0){
                throw  new RuntimeException("在businessWindowDelete操作中，第"+nos[i]+"数据异常");
            }
        }
        return rows;
    }

    @Override
    public int businessTypeUpdate(String no, BusinessType businessType) {
        return businessTypeDao.businessTypeUpdate(no,businessType);
    }

    @Override
    public List<BusinessType> businessTypeList(String condition,int currentPage,int pageSize) {
        return businessTypeDao.businessTypeList(condition,currentPage,pageSize);
    }

    @Override
    public Page<BusinessType> businessTypePages(String condition, int currentPage, int pageSize) {
        return businessTypeDao.businessTypePages(condition,currentPage,pageSize);
    }

    @Override
    public BusinessType businessTypeGetByNo(String no) {
        return businessTypeDao.businessTypeGetByNo(no);
    }

    @Override
    public List<BusinessType> typeList() {
        return businessTypeDao.typeList();
    }
}
