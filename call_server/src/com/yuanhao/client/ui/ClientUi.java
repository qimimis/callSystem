package com.yuanhao.client.ui;

import com.yuanhao.dto.ActionEnum;
import com.yuanhao.dto.ActionMsg;
import com.yuanhao.dto.ClientEnum;
import com.yuanhao.dto.HandlerMsg;
import com.yuanhao.manager.dao.entity.BusinessType;
import com.yuanhao.manager.dao.entity.BusinessWindow;
import com.yuanhao.manager.dao.entity.Ticket;
import com.yuanhao.manager.service.IBusinessTypeService;
import com.yuanhao.manager.service.IBusinessWindowService;
import com.yuanhao.manager.service.ITicketTodayService;
import com.yuanhao.manager.service.impl.BusinessTypeServiceImpl;
import com.yuanhao.manager.service.impl.BusinessWindowServiceImpl;
import com.yuanhao.manager.service.impl.TicketTodayServiceImpl;
import com.yuanhao.net.INet;
import com.yuanhao.net.impl.NetImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientUi extends JFrame {

    private JPanel contentPane;
    private JPanel rightPL;
    IBusinessTypeService businessTypeService = new BusinessTypeServiceImpl();
    ITicketTodayService ticketTodayService = new TicketTodayServiceImpl();
    private int countTicket = 1;
    private INet net;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClientUi frame = new ClientUi();
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
    public ClientUi() {
        net = NetImpl.getNetImpl();
        setMinimumSize(new Dimension(900, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 204, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel leftPL = new JPanel();
        leftPL.setPreferredSize(new Dimension(500, 10));
        contentPane.add(leftPL, BorderLayout.WEST);
        leftPL.setLayout(new BorderLayout(0, 0));

        rightPL = new JPanel();
        rightPL.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.add(rightPL, BorderLayout.CENTER);
        rightPL.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblNewLabel = new JLabel("\u70B9\u51FB\u4EE5\u4E0B\u4E1A\u52A1\u53D6\u7968");
        lblNewLabel.setForeground(SystemColor.textHighlight);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("����", Font.BOLD, 16));
        lblNewLabel.setPreferredSize(new Dimension(300, 40));
        rightPL.add(lblNewLabel);
        //初始化按钮
        initBT();

        JPanel topPL = new JPanel();
        topPL.setPreferredSize(new Dimension(10, 100));
        contentPane.add(topPL, BorderLayout.NORTH);

        JLabel topLB1 = new JLabel("XX\u94F6\u884C\u53D6\u7968\u7AEF");
        topLB1.setHorizontalAlignment(SwingConstants.CENTER);
        topLB1.setForeground(SystemColor.textHighlight);
        topLB1.setFont(new Font("����", Font.BOLD, 45));
        topLB1.setPreferredSize(new Dimension(600, 90));
        topPL.add(topLB1);

        JLabel topLB2 = new JLabel("image here");
        topLB2.setPreferredSize(new Dimension(90, 90));
        topPL.add(topLB2);
    }

    private void initBT() {
        //
//		List<BusinessType> typeList = businessTypeService.typeList();
        //
        ActionMsg am = new ActionMsg();
        am.setClientEnum(ClientEnum.TAKE_CLIENT);
        am.setActionEnum(ActionEnum.GET_BUSINESS);

        net.send(am);
        HandlerMsg hm = net.receive();
        List<BusinessType> typeList = (List<BusinessType>) hm.getDataMap().get("businessTypes");

        for (BusinessType type : typeList) {
            JButton btnNewButton = new JButton(type.getBusinessTypeCode() + "|" + type.getBusinessTypeName());
            btnNewButton.setPreferredSize(new Dimension(150, 30));
            btnNewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    takeTicket(e);
                }
            });
            rightPL.add(btnNewButton);
        }
    }

    private void takeTicket(ActionEvent e) {
        ActionMsg am = new ActionMsg();
        String btCode = e.getActionCommand().split("\\|")[0];
        String btName = e.getActionCommand().split("\\|")[1];
        am.setClientEnum(ClientEnum.TAKE_CLIENT);
        am.setActionEnum(ActionEnum.TAKE_TICKET);
        am.getDataMap().put("btCode",btCode);
        am.getDataMap().put("btName",btName);

        net.send(am);
        HandlerMsg hm = new HandlerMsg();
        hm = net.receive();
        Ticket ticket = (Ticket) hm.getDataMap().get("ticket");
        if (ticket == null) {
            JOptionPane.showConfirmDialog(null, "系统错误,达到取票上限，请下次再取");
        } else {
            TakeTicketDialogUi dialog = new TakeTicketDialogUi(ticket);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        }
//		String windowNo =getWindow(type);
//		countTicket++;
//		Ticket ticket = new Ticket(countTicket,type.getBusinessTypeCode(),type.getBusinessTypeName(),new Date(),"127.0.0.1",countTicket,null,0,"192.168.1.1",Integer.parseInt(windowNo),"",(byte)1,"");
//		int flag = ticketTodayService.ticketTodayAdd(ticket);
//		if(flag>0){
//			TakeTicketDialogUi dialog = new TakeTicketDialogUi(ticket);
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		}else {
//			JOptionPane.showConfirmDialog(null,"业务办理失败");
//		}
    }

    private String getWindow(BusinessType type) {
        IBusinessWindowService ibws = new BusinessWindowServiceImpl();
        List<BusinessWindow> windowList = ibws.businessWindowList(type.getBusinessTypeName(), 1, 5);
        String windowNo = null;
        for (BusinessWindow window : windowList) {
            windowNo = window.getBwNo();
        }
        return windowNo;
    }

}
