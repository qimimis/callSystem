package com.yuanhao.manager.dao;

import com.yuanhao.manager.dao.entity.Ticket;
import com.yuanhao.utils.Page;

import java.util.List;

public interface ITicketDao {


    int ticketAdd(Ticket ticket);

    int ticketDelete(int id);

    int ticketUpdate(int id, Ticket ticket);

    List<Ticket> ticketList(String condition, int currentPage, int pageSize);

    Page<Ticket> ticketPages(String condition, int currentPage, int pageSize);

    Ticket ticketGetById(int id);
}
