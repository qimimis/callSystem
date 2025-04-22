package com.yuanhao.action;

import com.yuanhao.dto.ActionEnum;
import com.yuanhao.dto.ActionMsg;
import com.yuanhao.dto.ClientEnum;
import com.yuanhao.dto.HandlerMsg;

import java.util.logging.Handler;

public interface IAction {
    public HandlerMsg handle(ActionMsg am);
}
