package com.yuanhao.server;

import com.yuanhao.dto.ActionMsg;

import java.net.ServerSocket;

public class Server extends Thread{
    private boolean flag ;
    private ActionMsg actionMsg;
    private ServerSocket ss;
    //TODO 还需构造ServerSocket的线程，负责接收数据处理数据返回给Server
    //Server的线程
    @Override
    public void run() {
        serverStart();
    }
    private void serverStart(){

    }

}
