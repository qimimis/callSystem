package com.yuanhao.manager.service;

import com.yuanhao.manager.dao.entity.Caller;
import com.yuanhao.utils.Page;

import java.util.List;

public interface ICallerService {
    List<Caller> callerList(String condition);
    int callerAdd(Caller caller);
    int callerDelete(String... nos);
    int callerUpdate(String no,Caller caller);
    Page<Caller> callerPages(String condition, int currentPage, int pageSize);
    Caller getByNo(String no);
    boolean login(String username,String password);
    int recall(int ticketId);
}
