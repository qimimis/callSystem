package com.yuanhao.caller.ui;

import com.yuanhao.dto.ActionEnum;
import com.yuanhao.dto.ActionMsg;
import com.yuanhao.dto.ClientEnum;
import com.yuanhao.dto.HandlerMsg;
import com.yuanhao.manager.dao.entity.Ticket;
import com.yuanhao.manager.service.ITicketService;
import com.yuanhao.manager.service.ITicketTodayService;
import com.yuanhao.manager.service.impl.TicketServiceImpl;
import com.yuanhao.manager.service.impl.TicketTodayServiceImpl;
import com.yuanhao.net.INet;
import com.yuanhao.net.impl.NetImpl;
import com.yuanhao.utils.Page;
import com.yuanhao.utils.SoundUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CallerClient extends JFrame {

    private JPanel contentPane;
    private JLabel callerNameLB;
    private JLabel windowNoLB;
    private JLabel typeLB;
    private JLabel currentTicketLB;
    private JLabel callTimesLB;

    ITicketTodayService ticketTodayService = new TicketTodayServiceImpl();
    ITicketService ticketService = new TicketServiceImpl();
    private int totalCount;
    private Page<Ticket> ticketTodayPage;
    private int callTimes;
    private int currentTicket;
    private int ticketNo;
    private INet net;
    private Ticket ticket;
    private String reason;
    private String windowNo;
    private String typeNo;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CallerClient frame = new CallerClient("192.168.0.1", "100002", "关羽", "1", "存款", "C");
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
    public CallerClient(String ip, String workNo, String name, String windowNo, String typeName, String typeNo) {
        //初始化
        this.windowNo = windowNo;
        this.typeNo = typeNo;
        setMinimumSize(new Dimension(600, 450));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel topPL = new JPanel();
        topPL.setPreferredSize(new Dimension(10, 250));
        contentPane.add(topPL, BorderLayout.NORTH);
        topPL.setLayout(null);

        callerNameLB = new JLabel("欢迎您：" + name);
        callerNameLB.setPreferredSize(new Dimension(100, 15));
        callerNameLB.setMinimumSize(new Dimension(100, 15));
        callerNameLB.setBounds(47, 31, 176, 40);
        topPL.add(callerNameLB);

        windowNoLB = new JLabel("当前窗口号为：" + windowNo);
        windowNoLB.setPreferredSize(new Dimension(100, 15));
        windowNoLB.setMinimumSize(new Dimension(100, 15));
        windowNoLB.setBounds(47, 88, 176, 40);
        topPL.add(windowNoLB);

        typeLB = new JLabel("当前业务：" + typeNo + "|" + typeName);
        typeLB.setPreferredSize(new Dimension(100, 15));
        typeLB.setMinimumSize(new Dimension(100, 15));
        typeLB.setBounds(47, 150, 176, 40);
        topPL.add(typeLB);

        currentTicketLB = new JLabel("New label");
        currentTicketLB.setPreferredSize(new Dimension(100, 15));
        currentTicketLB.setMinimumSize(new Dimension(100, 15));
        currentTicketLB.setBounds(250, 150, 120, 40);
        topPL.add(currentTicketLB);

        callTimesLB = new JLabel("New label");
        callTimesLB.setPreferredSize(new Dimension(100, 15));
        callTimesLB.setMinimumSize(new Dimension(100, 15));
        callTimesLB.setBounds(391, 150, 120, 40);
        topPL.add(callTimesLB);

        JPanel bottomPL = new JPanel();
        FlowLayout fl_bottomPL = (FlowLayout) bottomPL.getLayout();
        fl_bottomPL.setHgap(20);
        fl_bottomPL.setVgap(20);
        bottomPL.setBorder(new EmptyBorder(5, 5, 5, 0));
        contentPane.add(bottomPL, BorderLayout.CENTER);

        JButton nextBT = new JButton("\u4E0B\u4E00\u4E2A\u53F7");
        nextBT.setPreferredSize(new Dimension(250, 50));
        nextBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next(ip, workNo, windowNo);
                init();
            }
        });
        bottomPL.add(nextBT);

        JButton recallBT = new JButton("\u91CD\u65B0\u53EB\u53F7");
        recallBT.setPreferredSize(new Dimension(250, 50));
        recallBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recall();
                init();
            }
        });
        bottomPL.add(recallBT);

        JButton successBT = new JButton("\u53EB\u53F7\u6210\u529F");
        successBT.setPreferredSize(new Dimension(250, 50));
        successBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                success();
                init();
            }
        });
        bottomPL.add(successBT);

        JButton failBT = new JButton("\u53EB\u53F7\u5931\u8D25");
        failBT.setPreferredSize(new Dimension(250, 50));
        failBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fail();
                init();
            }
        });
        bottomPL.add(failBT);
        init();
    }

    private void init() {
//        ticketTodayPage = ticketTodayService.ticketTodayPages(typeNo, 1, 5);
//        totalCount = ticketTodayPage.getCount();
//        if (totalCount < 1) {
//            JOptionPane.showConfirmDialog(null, "已经没号了，下班！！");
//        }
//        callTimes = ticketTodayPage.getList().get(0).getTicketCallCount();
//        ticketNo = ticketTodayPage.getList().get(0).getTicketNo();
//        currentTicketLB.setText("当前票号是：" + ticketNo);
//        callTimesLB.setText("叫号次数：" + callTimes);
        if (ticket == null) {
            currentTicketLB.setText("当前票号是：" + "null");
            callTimesLB.setText("叫号次数：" + "null");
        }else {
            currentTicketLB.setText("当前票号是：" + ticket.getTicketId());
            callTimesLB.setText("叫号次数：" + totalCount);
        }

    }

    private void next(String ip, String workNo, String windowNo) {
        totalCount = 1;
        net = NetImpl.getNetImpl();
        ActionMsg am = new ActionMsg();
        am.setClientEnum(ClientEnum.CALL_CLIENT);
        am.setActionEnum(ActionEnum.CALL_NEXT);
//        am.getDataMap().put("ticketId", ticket.getTicketId());
        am.getDataMap().put("windowNo", windowNo);
        am.getDataMap().put("workNo", workNo);
        am.getDataMap().put("btCode", typeNo);
        boolean isHas = true;
        try {
            net.send(am);
            HandlerMsg hm = net.receive();
            ticket = (Ticket) hm.getDataMap().get("ticket");
            if (ticket == null) {
                isHas = false;
                JOptionPane.showConfirmDialog(null, "没号，下班");
            } else {
                String msg = "请" + ticket.getTicketId() + " 号，到 " + windowNo + " 号窗口办理";
                JOptionPane.showConfirmDialog(null, msg);
                SoundUtils.playSound(String.valueOf(ticket.getTicketId()),windowNo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        if (totalCount < 1) {
//            JOptionPane.showConfirmDialog(null, "已经没号了，下班！！");
//            currentTicketLB.setText("当前票号是：无");
//            callTimesLB.setText("叫号次数：无");
//        } else {
//
//            //获取当前叫号信息
//            int id = ticketTodayPage.getList().get(0).getTicketId();
//            Ticket ticket = ticketTodayService.ticketTodayGetById(id);
//            //先添加到ticket中(此处为上一个人的叫号)
//            //TODO 此处时间未来需修改成返回ticket值
//            //受不了了这些逻辑了，callTime设置成当前时间了，以后有缘再改！！
//            int flag = ticketService.ticketAdd(new Ticket(ticket.getTicketNo(), ticket.getTicketBusinessCode(), ticket.getTicketBusinessName(), ticket.getTicketTakeTime(), ticket.getTicketTakeIp(), ticket.getTicketWaitCount(), new Date(), ticket.getTicketCallCount(), ip, Integer.parseInt(windowNo), workNo, ticket.getTicketIsSuccess(), ticket.getTicketDesc()));
//            if (flag < 0) {
//                JOptionPane.showConfirmDialog(null, "添加出bug,请联系XXX");
//            } else {
//                //再删除tickettoday中的ticket
//                flag = ticketTodayService.ticketTodayDelete(id);
//                if (flag < 0) {
//                    JOptionPane.showConfirmDialog(null, "删除出bug,请联系XXX");
//                } else {
//                    JOptionPane.showConfirmDialog(null, "开始叫号");
//                }
//            }
//        }
    }

    private void recall() {
        net = NetImpl.getNetImpl();
        ActionMsg am = new ActionMsg();
        am.setClientEnum(ClientEnum.CALL_CLIENT);
        am.setActionEnum(ActionEnum.CALL_RECALL);
        am.getDataMap().put("ticketId", ticket.getTicketId());
        am.getDataMap().put("reason", reason);
        try {
            net.send(am);
            HandlerMsg hm = net.receive();
            boolean rs = (boolean) hm.getDataMap().get("result");
            if (rs) {
                totalCount++;
                String msg = "请" + ticket.getTicketId() + "到" + windowNo + "窗口办理";
                JOptionPane.showConfirmDialog(null, msg);
                SoundUtils.playSound(String.valueOf(ticket.getTicketId()),windowNo);
            } else {
                totalCount++;
                JOptionPane.showConfirmDialog(null, "叫号大于5次");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        if (totalCount < 1) {
//            JOptionPane.showConfirmDialog(null, "已经没号了，下班！！");
//        }else {
//            int id = ticketTodayPage.getList().get(0).getTicketId();
//            Ticket ticket =  ticketTodayPage.getList().get(0);
//            ticket.setTicketCallCount(ticket.getTicketCallCount()+1);
//            System.out.println(ticket.getTicketCallCount());
//            int flag = ticketTodayService.ticketTodayUpdate(id,ticket);
//            if (flag <1){
//                JOptionPane.showConfirmDialog(null, "重新叫号失败");
//            }else {
//                JOptionPane.showConfirmDialog(null, "重新叫号成功");
//            }
//        }

    }

    private void success() {

        net = NetImpl.getNetImpl();
        ActionMsg am = new ActionMsg();
        am.setClientEnum(ClientEnum.CALL_CLIENT);
        am.setActionEnum(ActionEnum.CALL_SUCCESS);
        am.getDataMap().put("ticketId", ticket.getTicketId());
        am.getDataMap().put("reason", reason);
        net.send(am);
        HandlerMsg hm = net.receive();
        if (hm.isFlag()) {
            JOptionPane.showConfirmDialog(null, hm.getMsg());
        }
//        if (totalCount < 1) {
//            JOptionPane.showConfirmDialog(null, "已经没号了，下班！！");
//        }else {
//            int id = ticketTodayPage.getList().get(0).getTicketId();
//            Ticket ticket = ticketTodayPage.getList().get(0);
//            ticket.setTicketIsSuccess((byte)1);
//            int flag = ticketTodayService.ticketTodayUpdate(id,ticket);
//            if (flag <1){
//                JOptionPane.showConfirmDialog(null, "本次叫号标记为成功");
//            }else {
//                JOptionPane.showConfirmDialog(null, "操作失败，模块为：叫号成功");
//            }
//
//        }
    }

    private void fail() {
        net = NetImpl.getNetImpl();
        ActionMsg am = new ActionMsg();
        am.setClientEnum(ClientEnum.CALL_CLIENT);
        am.setActionEnum(ActionEnum.CALL_FAIL);
        am.getDataMap().put("ticketId", ticket.getTicketId());
        am.getDataMap().put("reason", reason);
        net.send(am);
        HandlerMsg hm = net.receive();
        if(hm.isFlag()){
            JOptionPane.showConfirmDialog(null, "本次叫号标记为失败");
        }else {
            JOptionPane.showConfirmDialog(null, "操作失败，模块为：叫号失败");
        }

//        if (totalCount < 1) {
//            JOptionPane.showConfirmDialog(null, "已经没号了，下班！！");
//        }else {
//            int id = ticketTodayPage.getList().get(0).getTicketId();
//            Ticket ticket = ticketTodayPage.getList().get(0);
//            ticket.setTicketIsSuccess((byte)0);
//            int flag = ticketTodayService.ticketTodayUpdate(id,ticket);
//            if (flag <1){
//                JOptionPane.showConfirmDialog(null, "本次叫号标记为失败");
//            }else {
//                JOptionPane.showConfirmDialog(null, "操作失败，模块为：叫号失败");
//            }
//
//        }
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
