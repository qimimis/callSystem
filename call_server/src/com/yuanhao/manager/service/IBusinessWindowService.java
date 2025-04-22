package com.yuanhao.manager.service;

import com.yuanhao.manager.dao.entity.BusinessWindow;
import com.yuanhao.utils.Page;

import java.util.List;

public interface IBusinessWindowService {
    int businessWindowAdd(BusinessWindow businessWindow);
    int businessWindowDelete(String... nos);
    int businessWindowUpdate(String no,BusinessWindow businessWindow);
    List<BusinessWindow> businessWindowList(String condition,int currentPage,int pageSize);
    Page<BusinessWindow> businessWindowPages(String condition,int currentPage,int pageSize);
    BusinessWindow businessWindowGetByNo(String no);
}
