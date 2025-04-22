package com.yuanhao.test;

import com.yuanhao.manager.dao.ITicketTodayDao;
import com.yuanhao.manager.dao.entity.Ticket;
import com.yuanhao.manager.dao.impl.TicketTodaytDaoImpl;
import com.yuanhao.manager.service.ITicketTodayService;
import com.yuanhao.manager.service.impl.TicketTodayServiceImpl;

import java.util.List;

public class Test {
    public static void main(String[] args) {
      ITicketTodayService ticketTodayService = new TicketTodayServiceImpl();
//        Ticket ticket = ticketTodayService.ticketTodayGetByBusinessCode("C");
//        System.out.println(ticket);

        Ticket ticket = ticketTodayService.call("C","存款","127.168.0.1");
        System.out.println(ticket);
        ITicketTodayDao ticketTodayDao = new TicketTodaytDaoImpl();
        int waitCount = ticketTodayDao.ttGetWaitCount("C");
        int ticketNo = ticketTodayDao.ttGetTicketNo("C");
        int ticketLimit = ticketTodayDao.ttGetTicketLimit("C");
        System.out.println(waitCount+"  "+ticketNo+"   "+ticketLimit);
    }
}
