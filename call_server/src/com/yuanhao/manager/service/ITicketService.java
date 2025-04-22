package com.yuanhao.manager.service;

import com.yuanhao.manager.dao.entity.Ticket;
import com.yuanhao.utils.Page;

import java.util.List;

public interface ITicketService {
    int ticketAdd(Ticket ticket);
    int ticketDelete(int... ids);
    int ticketUpdate(int id,Ticket ticket);
    List<Ticket> ticketList(String condition, int currentPage, int pageSize);
    Page<Ticket> ticketPages(String condition, int currentPage, int pageSize);
    Ticket ticketGetById(int id);

}
