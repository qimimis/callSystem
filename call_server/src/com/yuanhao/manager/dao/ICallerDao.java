package com.yuanhao.manager.dao;

import com.yuanhao.manager.dao.entity.Caller;
import com.yuanhao.utils.Page;

import java.util.List;

public interface ICallerDao {
    List<Caller> callerList(String condition);

    int callerAdd(Caller caller);

    int callerDelete(String no);

    int callerUpdate(String no, Caller caller);

    Page<Caller> callerPages(String condition, int currentPage, int pageSize);

    Caller getByNo(String no);

    boolean login(String username, String password);
}
