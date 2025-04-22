package com.yuanhao.manager.service.impl;

import com.yuanhao.manager.dao.ITicketTodayDao;
import com.yuanhao.manager.dao.entity.Ticket;
import com.yuanhao.manager.dao.impl.TicketTodaytDaoImpl;
import com.yuanhao.manager.service.ITicketTodayService;
import com.yuanhao.utils.Page;

import java.util.Date;
import java.util.List;

public class TicketTodayServiceImpl implements ITicketTodayService {
    ITicketTodayDao ticketTodayDao = new TicketTodaytDaoImpl();
    private static final Object object = new Object();

    @Override
    public int ticketTodayAdd(Ticket ticket) {
        return ticketTodayDao.ticketTodayAdd(ticket);
    }

    @Override
    public int ticketTodayDelete(int... ids) {
        int rows = -1;
        for (int i = 0; i < ids.length; i++) {
            rows = ticketTodayDao.ticketTodayDelete(ids[i]);
            if (rows < 0) {
                throw new RuntimeException("在callerDelete操作中，第" + ids[i] + "数据异常");
            }
        }
        return rows;
    }

    @Override
    public int ticketTodayUpdate(int id, Ticket ticket) {
        return ticketTodayDao.ticketTodayUpdate(id, ticket);
    }

    @Override
    public List<Ticket> ticketTodayList(String condition, int currentPage, int pageSize) {
        return ticketTodayDao.ticketList(condition, currentPage, pageSize);
    }

    @Override
    public Page<Ticket> ticketTodayPages(String condition, int currentPage, int pageSize) {
        return ticketTodayDao.ticketPages(condition, currentPage, pageSize);
    }

    @Override
    public Ticket ticketTodayGetById(int id) {
        return ticketTodayDao.ticketGetById(id);
    }

    @Override
    public void fail(int ticketId, String reason) {
        ticketTodayDao.fail(ticketId, reason);
    }

    @Override
    public Ticket ticketTodayGetByBusinessCode(String btCode) {
        return ticketTodayDao.ticketTodayGetByBusinessCode(btCode);
    }

    @Override
    public Ticket call(String btCode, String btName, String takeIp) {
        Ticket ticket = null;
        synchronized (object) {
            int waitCount = ticketTodayDao.ttGetWaitCount(btCode);
            int ticketNo = ticketTodayDao.ttGetTicketNo(btCode);
            int ticketLimit = ticketTodayDao.ttGetTicketLimit(btCode);
            if (ticketNo > ticketLimit) {
                System.out.println("票满了");
            } else {
                ticket = new Ticket();
                ticket.setTicketNo(ticketNo + 1);
                ticket.setTicketTakeIp(takeIp);
                ticket.setTicketBusinessCode(btCode);
                ticket.setTicketBusinessName(btName);
                ticket.setTicketWaitCount(waitCount);
                ticket.setTicketTakeTime(new Date());
                int rows = ticketTodayDao.ticketTodayAdd(ticket);
                if (rows < 1) {
                    return null;
                }
            }
            return ticket;
        }


    }
}
