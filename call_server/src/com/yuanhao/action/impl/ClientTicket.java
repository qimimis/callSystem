package com.yuanhao.action.impl;

import com.yuanhao.action.IAction;
import com.yuanhao.dto.ActionMsg;
import com.yuanhao.dto.HandlerMsg;
import com.yuanhao.manager.dao.entity.Ticket;
import com.yuanhao.manager.service.ITicketTodayService;
import com.yuanhao.manager.service.impl.TicketTodayServiceImpl;
import com.yuanhao.server.ui.DisplayMsg;

public class ClientTicket implements IAction {

    ITicketTodayService ticketTodayService = new TicketTodayServiceImpl();


  @Override
  public HandlerMsg handle(ActionMsg am) {
        HandlerMsg hm = null;
        //btCode客户端与服务器端名字都是一样，服务器端才能接收客户端的数据
        String btCode = am.getDataMap().get("btCode").toString();
        String btName = am.getDataMap().get("btName").toString();
        String takeIp = am.getClientIp().toString();
        System.out.println(btCode+"  "+btName+"  "+takeIp);
//		String takeIp = am.getData().get("takeIp").toString();
        //调用业务层得到一张票
        try {
            Ticket ticket = ticketTodayService.call(btCode, btName, takeIp);
            hm = new HandlerMsg();
            hm.setFlag(true);
            //把一张票的信息返回给客户端
            //ticket的服务器与客户端名字一样才能让客户端接收回数据
            hm.getDataMap().put("ticket", ticket);
            DisplayMsg.displayActionMessage(takeIp + "机器取票成功");
            //	System.out.println("在服务器端的界面提示 "+takeIp+"机器取票成功");
        }
        catch (Exception e) {
            hm = new HandlerMsg();
            hm.setFlag(false);
            DisplayMsg.displayActionMessage(takeIp + "机器取票失败 ");
        }
        finally{
            return hm;
        }

    }

}
