package com.yuanhao.caller.test;

import com.yuanhao.manager.dao.entity.Ticket;
import com.yuanhao.manager.dao.entity.TicketToday;
import com.yuanhao.manager.service.ICallerService;
import com.yuanhao.manager.service.ITicketService;
import com.yuanhao.manager.service.ITicketTodayService;
import com.yuanhao.manager.service.impl.CallerServiceImpl;
import com.yuanhao.manager.service.impl.TicketServiceImpl;
import com.yuanhao.manager.service.impl.TicketTodayServiceImpl;
import com.yuanhao.utils.Page;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
//        ICallerService service = new CallerServiceImpl();
//        System.out.println(service.login("登陆成功"+"100002","123456"));
//        System.out.println(service.login("失败"+"100002","321654"));

        ITicketTodayService ticketTodayService = new TicketTodayServiceImpl();
        ITicketService ticketService = new TicketServiceImpl();
//        Page<Ticket> todayPage = ticketTodayService.ticketTodayPages("C",1,5);
//        System.out.println(todayPage);

//        int flag = ticketTodayService.ticketTodayAdd(new Ticket(7,"A","取款",new Date(),"127.0.0.1",6,0,new Date(),"192.168.1.1",1,"10002",(byte)1,""));
//        System.out.println(flag);
//        int flag = iTicketService.ticketAdd(new Ticket(7,"A","取款",new Date(),"127.0.0.1",6,null,0,null,0,null,(byte)1,""));
//        System.out.println(flag);
        Page<Ticket> ticketTodayPage = ticketTodayService.ticketTodayPages("",1,5);

        int id = ticketTodayPage.getList().get(0).getTicketId();
        Ticket ticket =  ticketTodayService.ticketTodayGetById(id);
        //先添加到ticket中(此处为上一个人的叫号)
        int flag = ticketService.ticketAdd(new Ticket(ticket.getTicketNo(), ticket.getTicketBusinessCode(), ticket.getTicketBusinessName(), new Date(), ticket.getTicketTakeIp(), ticket.getTicketWaitCount(),ticket.getTicketCallTime(), ticket.getTicketCallCount(), ticket.getTicketCallIp(), ticket.getTicketCallWindow(), ticket.getTicketCallerWorkno(), ticket.getTicketIsSuccess(), ticket.getTicketDesc()));
        System.out.println(flag);
    }
}
