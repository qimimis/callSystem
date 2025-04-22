package com.yuanhao.action.impl;

import com.yuanhao.action.IAction;
import com.yuanhao.dto.ActionEnum;
import com.yuanhao.dto.ActionMsg;
import com.yuanhao.dto.ClientEnum;
import com.yuanhao.dto.HandlerMsg;
import com.yuanhao.manager.dao.entity.Ticket;
import com.yuanhao.manager.service.ICallerService;
import com.yuanhao.manager.service.ITicketTodayService;
import com.yuanhao.manager.service.impl.CallerServiceImpl;
import com.yuanhao.manager.service.impl.TicketTodayServiceImpl;
import com.yuanhao.server.ui.DisplayMsg;

public class CallerRecall implements IAction {

    ICallerService callerService = new CallerServiceImpl();
    ITicketTodayService ticketTodayService = new TicketTodayServiceImpl();
    @Override
    public HandlerMsg handle(ActionMsg am) {
        HandlerMsg hm = null;
        Integer ticketId = (Integer) am.getDataMap().get("ticketId");
        try {
            int count = callerService.recall(ticketId);//重叫

            hm = new HandlerMsg();
            hm.setFlag(true);

            if (count > 4) {
                boolean result = false;
                hm.getDataMap().put("result", result);
                hm.setMsg("叫号次数大于5");

            } else {
                boolean result = true;
                hm.getDataMap().put("result", result);
            }
            String msg = am.getClientIp() + "第" + count + "次重叫";
            DisplayMsg.displayCallMessage(msg);
        } finally {

        }
        return hm;
    }
}
