package com.yuanhao.manager.dao;

import com.yuanhao.manager.dao.entity.BusinessWindow;
import com.yuanhao.utils.Page;

import java.util.List;

public interface IBusinessWindowDao {
    int businessWindowAdd(BusinessWindow businessWindow);

    int businessWindowDelete(String no);

    int businessWindowUpdate(String no, BusinessWindow businessWindow);

    List<BusinessWindow> businessWindowList(String condition,int currentPage,int pageSize);

    Page<BusinessWindow> businessWindowPages(String condition, int currentPage, int pageSize);

    BusinessWindow businessWindowGetByNo(String no);
}
