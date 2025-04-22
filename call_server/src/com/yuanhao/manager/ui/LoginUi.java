package com.yuanhao.manager.ui;

import com.yuanhao.manager.service.IAdminService;
import com.yuanhao.manager.service.impl.AdminServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUi extends JFrame {

    private JPanel contentPane;
    private JTextField usernameTF;
    private JTextField passwordTF;
	IAdminService adminService = new AdminServiceImpl();
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginUi frame = new LoginUi();
                    frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
    public LoginUi() {


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 429, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(10, 150));
        titlePanel.setSize(new Dimension(0, 150));
        contentPane.add(titlePanel, BorderLayout.NORTH);

        JLabel titleLB = new JLabel("");
        titleLB.setIcon(new ImageIcon("call_server/src/com/yuanhao/pic/loginMg.jpg"));
        titleLB.setBounds(0,-1,429,158);
        titlePanel.add(titleLB);

        JPanel loginPanel = new JPanel();
        contentPane.add(loginPanel, BorderLayout.SOUTH);

        JButton loginBT = new JButton("\u767B\u5F55");
        loginBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        loginPanel.add(loginBT);

        JButton resetBT = new JButton("\u91CD\u7F6E");
        resetBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        loginPanel.add(resetBT);

        JPanel infoPanel = new JPanel();
        contentPane.add(infoPanel, BorderLayout.CENTER);
        infoPanel.setLayout(null);

        JLabel passwordLB = new JLabel("\u5BC6\u7801\uFF1A");
        passwordLB.setHorizontalAlignment(SwingConstants.RIGHT);
        passwordLB.setFont(new Font("����", Font.PLAIN, 16));
        passwordLB.setBounds(95, 80, 65, 26);
        infoPanel.add(passwordLB);

        JLabel usernameLB = new JLabel("\u7528\u6237\u540D\uFF1A");
        usernameLB.setHorizontalAlignment(SwingConstants.RIGHT);
        usernameLB.setFont(new Font("����", Font.PLAIN, 16));
        usernameLB.setBounds(95, 39, 65, 26);
        infoPanel.add(usernameLB);

        usernameTF = new JTextField();
        usernameTF.setBounds(176, 42, 145, 23);
        infoPanel.add(usernameTF);
        usernameTF.setColumns(10);

        passwordTF = new JPasswordField();
        passwordTF.setColumns(10);
        passwordTF.setBounds(176, 82, 145, 23);
        infoPanel.add(passwordTF);
    }

    private void login() {
        String username = usernameTF.getText();
        String password = passwordTF.getText();

		boolean flag = false;
        if (username.isEmpty()|| password.isEmpty()) {
			JOptionPane.showConfirmDialog(null,"用户名和密码不得为空");
        }
		else {
			flag = adminService.adminLogin(username,password);
			if (flag == true){
                String ip = adminService.getIp(username);
				JOptionPane.showConfirmDialog(null,"登录成功");
                this.dispose();
                ManagerUi managerUi = new ManagerUi(username,ip);
                managerUi.setVisible(true);
			}else {
				JOptionPane.showConfirmDialog(null,"登陆失败");
			}
		}
    }

    private void reset() {
		usernameTF.setText("");
		passwordTF.setText("");
    }
}
