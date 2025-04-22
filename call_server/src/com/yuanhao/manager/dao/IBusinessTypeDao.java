package com.yuanhao.manager.dao;

import com.yuanhao.manager.dao.entity.BusinessType;
import com.yuanhao.utils.Page;

import java.util.List;

public interface IBusinessTypeDao {
    int businessTypeAdd(BusinessType businessType);

    int businessTypeDelete(String no);

    int businessTypeUpdate(String no, BusinessType businessType);

    List<BusinessType> businessTypeList(String condition,int currentPage,int pageSize);

    Page<BusinessType> businessTypePages(String condition, int currentPage, int pageSize);

    BusinessType businessTypeGetByNo(String no);

    List<BusinessType> typeList();

    BusinessType getBybtCode(String btCode);
}
