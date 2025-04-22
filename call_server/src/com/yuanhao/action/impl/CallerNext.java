package com.yuanhao.action.impl;

import com.yuanhao.action.IAction;
import com.yuanhao.dto.ActionMsg;
import com.yuanhao.dto.HandlerMsg;
import com.yuanhao.manager.dao.entity.Ticket;
import com.yuanhao.manager.service.ITicketTodayService;
import com.yuanhao.manager.service.impl.TicketTodayServiceImpl;

import javax.swing.*;

public class CallerNext implements IAction {

    ITicketTodayService todayService = new TicketTodayServiceImpl();

    @Override
    public HandlerMsg handle(ActionMsg am) {
        HandlerMsg hm = null;
        Ticket ticket = null;
        String btCode = am.getDataMap().get("btCode").toString();
        Integer bwNo = Integer.valueOf(am.getDataMap().get("windowNo").toString());
        String workNo = am.getDataMap().get("workNo").toString();
        String takeIp = am.getClientIp().toString();
        Integer ticketNo = 0;

        try {
            //
            ticket = todayService.ticketTodayGetByBusinessCode(btCode);
            ticket.setTicketCallerWorkno(workNo);
            ticket.setTicketCallCount(1);
            todayService.ticketTodayUpdate(ticket.getTicketId(), ticket);
            hm = new HandlerMsg();
            hm.setFlag(true);
            hm.getDataMap().put("ticket", ticket);
            if (ticket == null) {
                JOptionPane.showConfirmDialog(null, "没号了");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hm;
    }
    //
//            ticket = new Ticket(ticketNo,btCode,"",new Date(),takeIp,0,null,0,"",bwNo,workNo,(byte)0,"");
//            int isSuceess = todayService.ticketTodayAdd(ticket);
//            ticketNo = ticket.getTicketNo();
//            hm = new HandlerMsg();
//            hm.setFlag(true);
//            //把一张票的信息返回给客户端
//            //ticket的服务器与客户端名字一样才能让客户端接收回数据
//            hm.getDataMap().put("ticket", ticket);
//            DisplayMsg.displayCallMessage("请"+btCode+ticketNo+"号到"+bwNo+"号窗口办理");
}
