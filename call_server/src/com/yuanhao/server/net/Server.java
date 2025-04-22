package com.yuanhao.server.net;

import com.yuanhao.action.ActionFactory;
import com.yuanhao.action.IAction;
import com.yuanhao.dto.ActionMsg;
import com.yuanhao.dto.HandlerMsg;
import com.yuanhao.server.ui.DisplayMsg;
import com.yuanhao.utils.Configer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private boolean isRun = false;
    private ServerSocket ss;
    private Configer cfg;

    private class ServerThread extends Thread {
        private Socket socket;

        public ServerThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            ObjectOutputStream oos = null;
            ObjectInputStream ois = null;
            try {
                oos = new ObjectOutputStream(socket.getOutputStream());
                ois = new ObjectInputStream(socket.getInputStream());
                while (isRun) {
                    ActionMsg am = (ActionMsg) ois.readObject();
                    am.setClientIp(socket.getInetAddress());
                    IAction action = ActionFactory.getAction(am.getClientEnum(), am.getActionEnum());
                    HandlerMsg hm = action.handle(am);
                    oos.writeObject(hm);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                if (oos != null) {
                    try {
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    }

    @Override
    public void run() {
        try {
            startServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void startServer() throws IOException {
        cfg = new Configer("call_server/src/com/yuanhao/config/server.properties");
        String strPort = cfg.getKey("server.port");
        try {
            ss = new ServerSocket(Integer.valueOf(strPort));
            isRun = true;
            DisplayMsg.displayActionMessage("启动服务器，在"+strPort+"的端口中监听");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            while (true) {
                Socket socket = ss.accept();
                new ServerThread(socket).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void stopServer() {
    }

    public boolean isRun() {
        return isRun;
    }

    public void setRun(Boolean isRun) {
        this.isRun = isRun;
    }

}
