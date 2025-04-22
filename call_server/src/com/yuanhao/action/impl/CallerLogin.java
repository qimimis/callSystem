package com.yuanhao.action.impl;

import com.yuanhao.action.IAction;
import com.yuanhao.dto.ActionEnum;
import com.yuanhao.dto.ActionMsg;
import com.yuanhao.dto.ClientEnum;
import com.yuanhao.dto.HandlerMsg;
import com.yuanhao.manager.dao.entity.BusinessWindow;
import com.yuanhao.manager.dao.entity.Caller;
import com.yuanhao.manager.service.IBusinessWindowService;
import com.yuanhao.manager.service.ICallerService;
import com.yuanhao.manager.service.impl.BusinessWindowServiceImpl;
import com.yuanhao.manager.service.impl.CallerServiceImpl;

import java.util.List;

public class CallerLogin implements IAction {
    IBusinessWindowService businessWindowService = new BusinessWindowServiceImpl();

    ICallerService callService = new CallerServiceImpl();
    @Override
    public HandlerMsg handle(ActionMsg am){
        HandlerMsg hm = new HandlerMsg();
        hm.setFlag(true);
        List<BusinessWindow> bw = businessWindowService.businessWindowList("",1,100);
        hm.getDataMap().put("bw",bw);
        String userName = am.getDataMap().get("userName").toString();
        String password = am.getDataMap().get("password").toString();

        if (userName!="" && password!=""){
            boolean isYes = callService.login(userName, password);
                List<Caller> callers = callService.callerList(userName);
                Caller caller = callers.get(0);
                hm.getDataMap().put("isYes", isYes);
                hm.getDataMap().put("caller", caller);
        }
        return hm;

    }
}
