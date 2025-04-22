package com.yuanhao.manager.service.impl;

import com.yuanhao.manager.dao.ITicketDao;
import com.yuanhao.manager.dao.entity.Ticket;
import com.yuanhao.manager.dao.impl.TicketDaoImpl;
import com.yuanhao.manager.service.ITicketService;
import com.yuanhao.utils.Page;

import java.util.List;

public class TicketServiceImpl implements ITicketService {
    ITicketDao ticketDao = new TicketDaoImpl();


    @Override
    public int ticketAdd(Ticket ticket) {
        return ticketDao.ticketAdd(ticket);
    }

    @Override
    public int ticketDelete(int... ids) {
        int rows = -1;
        for (int i = 0; i < ids.length; i++) {
            rows =  ticketDao.ticketDelete(ids[i]);
            if (rows < 0){
                throw  new RuntimeException("在callerDelete操作中，第"+ids[i]+"数据异常");
            }
        }
        return rows;
    }

    @Override
    public int ticketUpdate(int id, Ticket ticket) {
        return ticketDao.ticketUpdate(id,ticket);
    }

    @Override
    public List<Ticket> ticketList(String condition, int currentPage, int pageSize) {
        return ticketDao.ticketList(condition,currentPage,pageSize);
    }

    @Override
    public Page<Ticket> ticketPages(String condition, int currentPage, int pageSize) {
        return ticketDao.ticketPages(condition,currentPage,pageSize);
    }

    @Override
    public Ticket ticketGetById(int id) {
        return ticketDao.ticketGetById(id);
    }
}
