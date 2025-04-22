package com.yuanhao.caller.ui;

import com.yuanhao.dto.ActionEnum;
import com.yuanhao.dto.ActionMsg;
import com.yuanhao.dto.ClientEnum;
import com.yuanhao.dto.HandlerMsg;
import com.yuanhao.manager.dao.entity.BusinessType;
import com.yuanhao.manager.dao.entity.BusinessWindow;
import com.yuanhao.manager.dao.entity.Caller;
import com.yuanhao.manager.service.IBusinessTypeService;
import com.yuanhao.manager.service.IBusinessWindowService;
import com.yuanhao.manager.service.impl.BusinessTypeServiceImpl;
import com.yuanhao.manager.service.impl.BusinessWindowServiceImpl;
import com.yuanhao.net.INet;
import com.yuanhao.net.impl.NetImpl;
import com.yuanhao.utils.Page;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginUi extends JFrame {

    private JPanel contentPane;
    private JTextField usernameTF;
    private JTextField passwordTF;
    private JComboBox windowCB;
    private JComboBox typeCB;
    private IBusinessWindowService ibws = new BusinessWindowServiceImpl();
    private IBusinessTypeService ibts = new BusinessTypeServiceImpl();
    private INet net;
    private List<BusinessWindow> btList;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginUi frame = new LoginUi();
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
        net = NetImpl.getNetImpl();
        initBusinessWindow();

        setMinimumSize(new Dimension(900, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JPanel titlePL = new JPanel();
        titlePL.setPreferredSize(new Dimension(880, 100));
        contentPane.add(titlePL);
        titlePL.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel titleLB = new JLabel("Welcome to login");
        titleLB.setHorizontalAlignment(SwingConstants.CENTER);
        titleLB.setForeground(SystemColor.textHighlight);
        titleLB.setFont(new Font("΢���ź�", Font.BOLD, 49));
        titleLB.setPreferredSize(new Dimension(770, 90));
        titlePL.add(titleLB);

        JLabel titleImgLB = new JLabel("IMG HERE");
        titleImgLB.setPreferredSize(new Dimension(90, 90));
        titlePL.add(titleImgLB);

        JPanel msgPL = new JPanel();
        msgPL.setPreferredSize(new Dimension(880, 440));
        contentPane.add(msgPL);
        msgPL.setLayout(null);

        JLabel windowLB = new JLabel("\u7A97\u53E3\u53F7\uFF1A");
        windowLB.setForeground(SystemColor.textHighlight);
        windowLB.setFont(new Font("����", Font.BOLD, 16));
        windowLB.setBounds(329, 85, 104, 28);
        msgPL.add(windowLB);

        JLabel typeLB = new JLabel("\u4E1A\u52A1\u7C7B\u578B\uFF1A");
        typeLB.setForeground(SystemColor.textHighlight);
        typeLB.setFont(new Font("����", Font.BOLD, 16));
        typeLB.setBounds(466, 85, 104, 28);
        msgPL.add(typeLB);

        JLabel usernameLB = new JLabel("\u5DE5\u53F7\uFF1A");
        usernameLB.setForeground(SystemColor.textHighlight);
        usernameLB.setHorizontalAlignment(SwingConstants.RIGHT);
        usernameLB.setFont(new Font("����", Font.BOLD, 16));
        usernameLB.setBounds(194, 174, 104, 28);
        msgPL.add(usernameLB);

        JLabel passwordLB = new JLabel("\u5BC6\u7801\uFF1A");
        passwordLB.setForeground(SystemColor.textHighlight);
        passwordLB.setHorizontalAlignment(SwingConstants.RIGHT);
        passwordLB.setFont(new Font("����", Font.BOLD, 16));
        passwordLB.setBounds(194, 224, 104, 28);
        msgPL.add(passwordLB);

        windowCB = new JComboBox();
        windowCB.setBounds(301, 133, 104, 28);
        String[] windowNo = new String[btList.size()];
        for (int i = 0; i < btList.size(); i++) {
            windowNo[i] = btList.get(i).getBwNo();
        }
        windowCB.setModel(new DefaultComboBoxModel(windowNo));
        msgPL.add(windowCB);

        typeCB = new JComboBox();
        typeCB.setBounds(441, 133, 104, 28);
        String[] windowtype = new String[btList.size()];
        for (int i = 0; i < btList.size(); i++) {
            windowtype[i] = btList.get(i).getBwTypeCode() + "|" + btList.get(i).getBwTypeName();
        }
        typeCB.setModel(new DefaultComboBoxModel(windowtype));
        msgPL.add(typeCB);

        usernameTF = new JTextField();
        usernameTF.setBounds(308, 178, 233, 24);
        msgPL.add(usernameTF);
        usernameTF.setColumns(10);

        passwordTF = new JTextField();
        passwordTF.setColumns(10);
        passwordTF.setBounds(308, 224, 233, 24);
        msgPL.add(passwordTF);

        JButton loginBT = new JButton("\u767B\u5F55");
        loginBT.setBounds(330, 278, 193, 42);
        loginBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        msgPL.add(loginBT);
    }
    private void initBusinessWindow() {

        ActionMsg am = new ActionMsg();
        am.getDataMap().put("userName", "");
        am.getDataMap().put("password", "");
        //取号端的客户端
        am.setClientEnum(ClientEnum.CALL_CLIENT);
        am.setActionEnum(ActionEnum.CALL_LOGIN);
        //发送数据到服务器端
        net.send(am);
        HandlerMsg hm = net.receive();
        //客户端与服务器端的businessType要一样才能拿到值。
        btList = (List<BusinessWindow>) hm.getDataMap().get("bw");

    }
    private void login() {

        //
        String userName = usernameTF.getText();
        String passWord = passwordTF.getText();
        String windowNo = windowCB.getSelectedItem().toString();
        String[] typeMsg = typeCB.getSelectedItem().toString().split("\\|");
        String typeNo = typeMsg[0];
        String typeName = typeMsg[1];
        ActionMsg am = new ActionMsg();
        am.setClientEnum(ClientEnum.CALL_CLIENT);
        am.setActionEnum(ActionEnum.CALL_LOGIN);//登录
        am.getDataMap().put("userName", userName);
        am.getDataMap().put("password", passWord);
        try {
            net = NetImpl.getNetImpl();
            net.send(am);
            HandlerMsg hm = net.receive();
            Caller caller = (Caller) hm.getDataMap().get("caller");
            boolean isYes = (boolean) hm.getDataMap().get("isYes");
            if (isYes) {
                dispose();
                CallerClient frame = new CallerClient(caller.getCallerLastLoginIp(), caller.getCallerWorkNo(), caller.getCallerName(), windowNo, typeName, typeNo);
                frame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "柜员编号或密码错误");
            }
            //
//		String workNo =  usernameTF.getText();
//		String password = passwordTF.getText();
//		String windowNo = windowCB.getSelectedItem().toString();
//		String[] typeMsg = typeCB.getSelectedItem().toString().split("\\|");
//		String typeNo = typeMsg[0];
//		String typeName = typeMsg[1];
//		ICallerService callerService = new CallerServiceImpl();
//		boolean flag = callerService.login(workNo,password);
//		if (flag == true){
//			BusinessWindow bw = new BusinessWindow(windowNo,typeNo,typeName);
//			int windowFlag = ibws.businessWindowUpdate(windowNo,bw);
//			if (windowFlag >0){
//				Caller caller = callerService.getByNo(workNo);
//				String ip = caller.getCallerLastLoginIp();
//				String name = caller.getCallerName();
//				JOptionPane.showConfirmDialog(null, "login success");
//				CallerClient frame = new CallerClient(ip,workNo,name,windowNo,typeName,typeNo);
//				frame.setVisible(true);
//			}else {
//				JOptionPane.showConfirmDialog(null, "login fail");
//			}
//		}else{
//			JOptionPane.showConfirmDialog(null, "login fail,password or username wrong");
//			usernameTF.setText("");
//			passwordTF.setText("");
//		}
        }finally {

        }
    }
}
