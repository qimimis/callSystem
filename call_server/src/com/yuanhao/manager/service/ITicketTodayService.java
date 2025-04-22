package com.yuanhao.manager.service;

import com.yuanhao.manager.dao.entity.Ticket;
import com.yuanhao.utils.Page;

import java.util.List;

public interface ITicketTodayService {
    int ticketTodayAdd(Ticket ticket);
    int ticketTodayDelete(int... ids);
    int ticketTodayUpdate(int id,Ticket ticket);
    List<Ticket> ticketTodayList(String condition, int currentPage, int pageSize);
    Page<Ticket> ticketTodayPages(String condition, int currentPage, int pageSize);
    Ticket ticketTodayGetById(int id);
    void fail(int ticketId, String reason);
    Ticket ticketTodayGetByBusinessCode(String btCode);
    Ticket call(String btCode,String btName,String takeIp);

}
