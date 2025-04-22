package com.yuanhao.net;

import com.yuanhao.dto.ActionMsg;
import com.yuanhao.dto.HandlerMsg;

public interface INet {
    /**
     * 发送数据到服务器
     * @param am
     */
    public void send(ActionMsg am)  ;
    /**
     * 接收从服务器返回的数据
     * @return
     */
    public HandlerMsg receive()  ;
}