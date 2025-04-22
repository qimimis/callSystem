package com.yuanhao.manager.service.impl;

import com.yuanhao.manager.dao.ICallerDao;
import com.yuanhao.manager.dao.ITicketTodayDao;
import com.yuanhao.manager.dao.entity.Caller;
import com.yuanhao.manager.dao.entity.Ticket;
import com.yuanhao.manager.dao.impl.CallerDaoImpl;
import com.yuanhao.manager.dao.impl.TicketTodaytDaoImpl;
import com.yuanhao.manager.service.ICallerService;
import com.yuanhao.utils.Page;

import java.util.List;

public class CallerServiceImpl implements ICallerService {
    ICallerDao iCallerDao = new CallerDaoImpl();
    @Override
    public List<Caller> callerList(String condition) {
        return iCallerDao.callerList(condition);
    }

    @Override
    public int callerAdd(Caller caller) {
        return iCallerDao.callerAdd(caller);
    }

    @Override
    public int callerDelete(String... nos) {
        int rows = -1;
        for (int i = 0; i < nos.length; i++) {
           rows =  iCallerDao.callerDelete(nos[i]);
           if (rows < 0){
               throw  new RuntimeException("在callerDelete操作中，第"+nos[i]+"数据异常");
           }
        }
        return rows;
    }

    @Override
    public int callerUpdate(String no, Caller caller) {
        return iCallerDao.callerUpdate(no,caller);
    }

    @Override
    public Page<Caller> callerPages(String condition, int currentPage, int pageSize) {
        return iCallerDao.callerPages(condition, currentPage,pageSize);
    }

    @Override
    public Caller getByNo(String no) {
        return iCallerDao.getByNo(no);
    }

    @Override
    public boolean login(String username, String password) {
        return iCallerDao.login(username,password);
    }
    ITicketTodayDao ticketTodayDao = new TicketTodaytDaoImpl();
    @Override
    public int recall(int ticketId) {
        int count = ticketTodayDao.ticketGetById(ticketId).getTicketCallCount();
        count++;
        Ticket ticket = ticketTodayDao.ticketGetById(ticketId);
        ticket.setTicketCallCount(count);
        ticketTodayDao.ticketTodayUpdate(ticketId,ticket);
        return count;
    }
}
