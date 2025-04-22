package com.yuanhao.manager.service.impl;

import com.yuanhao.manager.dao.IBusinessWindowDao;
import com.yuanhao.manager.dao.entity.BusinessWindow;
import com.yuanhao.manager.dao.impl.BusinessWindowDaoImpl;
import com.yuanhao.manager.service.IBusinessWindowService;
import com.yuanhao.utils.Page;

import java.util.List;

public class BusinessWindowServiceImpl implements IBusinessWindowService {
    IBusinessWindowDao businessWindowDao = new BusinessWindowDaoImpl();
    @Override
    public int businessWindowAdd( BusinessWindow businessWindow) {
        return businessWindowDao.businessWindowAdd(businessWindow);
    }

    @Override
    public int businessWindowDelete(String... nos ) {
        int rows = -1;
        for (int i = 0; i < nos.length; i++) {
            rows =  businessWindowDao.businessWindowDelete(nos[i]);
            if (rows < 0){
                throw  new RuntimeException("在businessWindowDelete操作中，第"+nos[i]+"数据异常");
            }
        }
        return rows;
    }

    @Override
    public int businessWindowUpdate(String no, BusinessWindow businessWindow) {
        return businessWindowDao.businessWindowUpdate( no,businessWindow);
    }

    @Override
    public List<BusinessWindow> businessWindowList(String condition,int currentPage,int pageSize) {
        return businessWindowDao.businessWindowList(condition,currentPage,pageSize);
    }

    @Override
    public Page<BusinessWindow> businessWindowPages(String condition, int currentPage, int pageSize) {
        return businessWindowDao.businessWindowPages(condition,currentPage,pageSize);
    }

    @Override
    public BusinessWindow businessWindowGetByNo(String no) {
        return businessWindowDao.businessWindowGetByNo(no);
    }
}
