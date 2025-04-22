package com.yuanhao.action.impl;

import com.yuanhao.action.IAction;
import com.yuanhao.dto.ActionMsg;
import com.yuanhao.dto.HandlerMsg;
import com.yuanhao.manager.dao.entity.BusinessType;
import com.yuanhao.manager.service.IAdminService;
import com.yuanhao.manager.service.IBusinessTypeService;
import com.yuanhao.manager.service.impl.BusinessTypeServiceImpl;
import com.yuanhao.server.ui.DisplayMsg;

import java.util.List;

public class ClientGetBusiness implements IAction {
    IBusinessTypeService BusinessTypeService = new BusinessTypeServiceImpl();
    @Override
    public HandlerMsg handle(ActionMsg am)  {
        HandlerMsg hm = null;
        //模拟从数据库查出想要的数据
        hm = new HandlerMsg();
        DisplayMsg.displayActionMessage(am.getClientIp()+"获取全部业务类型并初始化界面完成");
        hm.setFlag(true);
        List<BusinessType> businessTypes = null;
        try {
            businessTypes = BusinessTypeService.typeList();
        }catch (Exception e){
            e.printStackTrace();
        }
        //客户端与服务器端的businessType要一样才能拿到值。
        hm.getDataMap().put("businessTypes", businessTypes);
        return hm;
    }
}
