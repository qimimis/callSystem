package com.yuanhao.action.impl;

import com.yuanhao.action.IAction;
import com.yuanhao.dto.ActionMsg;
import com.yuanhao.dto.HandlerMsg;
import com.yuanhao.manager.dao.entity.Ticket;
import com.yuanhao.manager.service.ITicketTodayService;
import com.yuanhao.manager.service.impl.TicketTodayServiceImpl;
import com.yuanhao.server.ui.DisplayMsg;

public class CallerSuccess implements IAction {
    ITicketTodayService ticketTodayService = new TicketTodayServiceImpl();

    @Override
    public HandlerMsg handle(ActionMsg am) {
        HandlerMsg hm = null;
        int ticketId = (int) am.getDataMap().get("ticketId");
        try {
            Ticket ticket = ticketTodayService.ticketTodayGetById(ticketId);
            ticket.setTicketIsSuccess((byte) 1);
            ticketTodayService.ticketTodayUpdate(ticketId, ticket);
            hm = new HandlerMsg();
            hm.setFlag(true);
            hm.setMsg("办理成功");
            DisplayMsg.displayCallMessage(am.getClientIp() + "叫号成功");
        } finally {

        }
        return hm;
    }
}
