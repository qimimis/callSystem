package com.yuanhao.action.impl;

import com.yuanhao.action.IAction;
import com.yuanhao.dto.ActionEnum;
import com.yuanhao.dto.ActionMsg;
import com.yuanhao.dto.ClientEnum;
import com.yuanhao.dto.HandlerMsg;
import com.yuanhao.manager.service.ICallerService;
import com.yuanhao.manager.service.ITicketTodayService;
import com.yuanhao.manager.service.impl.CallerServiceImpl;
import com.yuanhao.manager.service.impl.TicketTodayServiceImpl;
import com.yuanhao.server.ui.DisplayMsg;

public class CallerFail implements IAction {

    ITicketTodayService ticketTodayService = new TicketTodayServiceImpl();
    @Override
    public HandlerMsg handle(ActionMsg am) {
        HandlerMsg hm = null;
        int ticketId = (int) am.getDataMap().get("ticketId");
        String reason = (String) am.getDataMap().get("reason");

            ticketTodayService.fail(ticketId, reason);
            hm = new HandlerMsg();
            hm.setFlag(true);
            hm.setMsg("办理失败success");
//        } catch (Exception e) {
//            hm.setFlag(false);
//            hm.setMsg("办理失败");
//            DisplayMsg.displayCallMessage(am.getClientIp()+":"+reason+",办理失败");
//            e.printStackTrace();
//        }
        return hm;
    }


}
