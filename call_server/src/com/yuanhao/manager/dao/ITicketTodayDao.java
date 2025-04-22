package com.yuanhao.manager.dao;

import com.yuanhao.manager.dao.entity.Ticket;
import com.yuanhao.utils.Page;

import java.util.List;

public interface ITicketTodayDao {

    int ticketTodayAdd(Ticket ticket);

    int ticketTodayDelete(int id);

    int ticketTodayUpdate(int id, Ticket ticket);

    List<Ticket> ticketList(String condition, int currentPage, int pageSize);

    Page<Ticket> ticketPages(String condition, int currentPage, int pageSize);

    Ticket ticketGetById(int id);

    void recall(int ticketId);

    void fail(int ticketId, String reason);

   Ticket ticketTodayGetByBusinessCode(String btCode);

    int ttGetWaitCount(String btCode);

    int ttGetTicketNo(String btCode);

    int ttGetTicketLimit(String btCode);
}
