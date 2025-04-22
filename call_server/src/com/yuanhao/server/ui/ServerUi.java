package com.yuanhao.server.ui;

import com.yuanhao.server.net.Server;
import com.yuanhao.utils.Configer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerUi extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ServerUi frame = new ServerUi();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ServerUi() {
        setMinimumSize(new Dimension(400, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);

        JButton startBT = new JButton("启动服务器");
        startBT.setPreferredSize(new Dimension(93, 40));
        startBT.addActionListener(new ActionListener() {
            Server server = null;

            @Override
            public void actionPerformed(ActionEvent e) {
                if ("启动服务器".equals(e.getActionCommand())){
                    startBT.setText("关闭服务器");
                    if (server != null) {
                        Configer cfg = new Configer("call_server/src/com/yuanhao/config/server.properties");
                        String strPort = cfg.getKey("server.port");
                        DisplayMsg.displayActionMessage("启动服务器，在"+strPort+"的端口中监听");
                        server.setRun(true);
                        if (server.isAlive()) {
                            return;
                        }
                    }
                    server = new Server();
                    server.start();
                }else {
                    startBT.setText("启动服务器");
                    server.stopServer();
                }

            }
        });
        panel.add(startBT);

        JSplitPane splitPane = new JSplitPane();
        contentPane.add(splitPane, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane();
        splitPane.setLeftComponent(scrollPane);

        JTextPane textPane = new JTextPane();
        textPane.setPreferredSize(new Dimension(195, 21));
        scrollPane.setViewportView(textPane);

        JScrollPane scrollPane_1 = new JScrollPane();
        splitPane.setRightComponent(scrollPane_1);

        JTextPane textPane_1 = new JTextPane();
        scrollPane_1.setViewportView(textPane_1);

        DisplayMsg.callMsgTP = textPane;
        DisplayMsg.actionMsgTP = textPane_1;
    }


}
