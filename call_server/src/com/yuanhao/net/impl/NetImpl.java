package com.yuanhao.net.impl;

import com.yuanhao.dto.ActionMsg;
import com.yuanhao.dto.HandlerMsg;
import com.yuanhao.net.INet;
import com.yuanhao.utils.Configer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NetImpl implements INet {
    private static NetImpl net;
    //单例设计模式
    public static NetImpl getNetImpl() {
        if (net == null) {
            net = new NetImpl();
        }
        return net;
    }

    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Configer cfg;
    private NetImpl(){
        cfg = new Configer("call_server/src/com/yuanhao/config/server.properties");
        String port = cfg.getKey("server.port");
        String ip = cfg.getKey("server.ip");
        try {
            //跟服务器建立连接
            socket = new Socket(ip, Integer.parseInt(port));
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送数据到服务器
     * @param am
     */
    public void send(ActionMsg am)  {
        try {
            oos.writeObject(am);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 接收从服务器返回的数据
     * @return
     */
    public HandlerMsg receive() {
        HandlerMsg hm = null;
        try {
            hm = (HandlerMsg) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        return hm;
    }
    //关闭资源
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (ois != null) {
            ois.close();
        }
        if (oos != null) {
            oos.close();
        }if (socket != null) {
            socket.close();
        }
    }
}
