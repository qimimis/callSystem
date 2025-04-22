package com.yuanhao.manager.ui;

import com.yuanhao.manager.service.IBusinessTypeService;
import com.yuanhao.manager.service.IBusinessWindowService;
import com.yuanhao.manager.service.ICallerService;
import com.yuanhao.manager.service.ITicketService;
import com.yuanhao.manager.service.impl.BusinessTypeServiceImpl;
import com.yuanhao.manager.service.impl.BusinessWindowServiceImpl;
import com.yuanhao.manager.service.impl.CallerServiceImpl;
import com.yuanhao.manager.service.impl.TicketServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ManagerUi extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private JTextField searchTF;
    private JTextField currentPageTF;
    private JPanel callerPL;
    private JPanel windowPL;
    private JPanel typePL;
    private JPanel ticketPL;
    private JTabbedPane rightP;
    private JLabel pageInfoLB;
    private JLabel timeLB;
    private int currentPage = 1;
    private int totalPage;

    ICallerService callerService = new CallerServiceImpl();
    IBusinessWindowService windowService = new BusinessWindowServiceImpl();
    IBusinessTypeService typeService = new BusinessTypeServiceImpl();
    ITicketService ticketService = new TicketServiceImpl();
    private String objectName;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ManagerUi frame = new ManagerUi("username", "ip");
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

    public ManagerUi(String username, String ip) {


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1300, 700);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel topPL = new JPanel();
        topPL.setMinimumSize(new Dimension(10, 100));
        topPL.setPreferredSize(new Dimension(10, 100));
        contentPane.add(topPL, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel(new ImageIcon("call_server/src/com/yuanhao/pic/icon-home.png"));
        lblNewLabel.setPreferredSize(new Dimension(80, 80));
        topPL.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("\u6392\u961F\u53EB\u53F7\u7BA1\u7406\u7CFB\u7EDF");
        lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 40));
        lblNewLabel_1.setPreferredSize(new Dimension(500, 80));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        topPL.add(lblNewLabel_1);

        JPanel bottomPL = new JPanel();
        bottomPL.setMinimumSize(new Dimension(10, 50));
        bottomPL.setPreferredSize(new Dimension(10, 50));
        contentPane.add(bottomPL, BorderLayout.SOUTH);

        JLabel bottomNameLB = new JLabel("@XX(广东)有限公司");
        bottomNameLB.setPreferredSize(new Dimension(900, 20));
        bottomNameLB.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPL.add(bottomNameLB);



        //刷新时间

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
         timeLB = new JLabel(simpleDateFormat.format(date));
        timeLB.setPreferredSize(new Dimension(900, 20));
        timeLB.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPL.add(timeLB);
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                timeLB.setText(simpleDateFormat.format(date));
            }
        });
        timer.start();


        JSplitPane splitPane = new JSplitPane();
        contentPane.add(splitPane, BorderLayout.CENTER);

        JPanel leftPL = new JPanel();
        leftPL.setBorder(new EmptyBorder(5, 5, 5, 5));
        leftPL.setMinimumSize(new Dimension(200, 10));
        leftPL.setPreferredSize(new Dimension(200, 10));
        splitPane.setLeftComponent(leftPL);
        leftPL.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        //左界面按钮，负责身生成caller，window，type界面
        JButton callerBT = new JButton("\u67DC\u5458\u7BA1\u7406", new ImageIcon("call_server/src/com/yuanhao/pic/icon-guiyuan.png"));
        callerBT.setPreferredSize(new Dimension(190, 50));
        callerBT.setMaximumSize(new Dimension(190, 40));
        callerBT.setMinimumSize(new Dimension(190, 40));
        callerBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objectName = "caller";
                if (callerPL == null) {
                    callerPL = new JPanel();
                    MakerUi caller = new MakerUi(callerPL, objectName);
//                    panelMaker(callerPL, objectName);
                    rightP.addTab("\u67DC\u5458\u754C\u9762", null, callerPL, null);
                    callerPL.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

                }
                int index = rightP.indexOfTab("\u67DC\u5458\u754C\u9762");
                rightP.setSelectedIndex(index);
            }
        });
        leftPL.add(callerBT);

        JButton windowBT = new JButton("\u7A97\u53E3\u7BA1\u7406", new ImageIcon("call_server/src/com/yuanhao/pic/icon-chuangkou.png"));
        windowBT.setPreferredSize(new Dimension(190, 50));
        windowBT.setMaximumSize(new Dimension(190, 40));
        windowBT.setMinimumSize(new Dimension(190, 40));
        windowBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objectName = "window";
                if (windowPL == null) {
                    windowPL = new JPanel();
                    MakerUi window = new MakerUi(windowPL, objectName);
//                    panelMaker(windowPL, objectName);
                    rightP.addTab("\u7A97\u53E3\u7BA1\u7406", null, windowPL, null);
                    windowPL.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

                }
                int index = rightP.indexOfTab("\u7A97\u53E3\u7BA1\u7406");
                rightP.setSelectedIndex(index);

            }
        });
        leftPL.add(windowBT);

        JButton typeBT = new JButton("\u4E1A\u52A1\u7BA1\u7406", new ImageIcon("call_server/src/com/yuanhao/pic/icon-yewu.png"));
        typeBT.setPreferredSize(new Dimension(190, 50));
        typeBT.setMaximumSize(new Dimension(190, 40));
        typeBT.setMinimumSize(new Dimension(190, 40));
        typeBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objectName = "type";
                if (typePL == null) {
                    typePL = new JPanel();
                    MakerUi type = new MakerUi(typePL, objectName);
                    rightP.addTab("\u4E1A\u52A1\u7BA1\u7406", null, typePL, null);
                }
                int index = rightP.indexOfTab("\u4E1A\u52A1\u7BA1\u7406");
                rightP.setSelectedIndex(index);

            }
        });
        leftPL.add(typeBT);

        JButton ticketBT = new JButton("\u529E\u7406\u60C5\u51B5\u67E5\u8BE2", new ImageIcon("call_server/src/com/yuanhao/pic/icon-chakan.png"));
        ticketBT.setPreferredSize(new Dimension(190, 50));
        ticketBT.setMaximumSize(new Dimension(190, 40));
        ticketBT.setMinimumSize(new Dimension(190, 40));
        ticketBT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                objectName = "ticket";
                if (ticketPL == null) {
                    ticketPL = new JPanel();
                    MakerTicketUi ticket = new MakerTicketUi(ticketPL, objectName);
                    rightP.addTab("办理状况", (Icon) null, ManagerUi.this.ticketPL, (String) null);
                }
                int index = ManagerUi.this.rightP.indexOfTab("办理状况");
                ManagerUi.this.rightP.setSelectedIndex(index);

            }
        });
        leftPL.add(ticketBT);
        //创建右分屏
        this.rightP = new JTabbedPane(1);
        splitPane.setRightComponent(this.rightP);
        JPanel userPL = new JPanel();
        //用户界面
        this.rightP.addTab("欢迎界面", (Icon) null, userPL, (String) null);
        userPL.setLayout((LayoutManager) null);
        JLabel userLB = new JLabel("用户名为:" + username);
        userLB.setFont(new Font("1", 0, 20));
        userLB.setBounds(82, 59, 307, 68);
        userPL.add(userLB);
        JLabel ipLB = new JLabel("用户ip为：" + ip);
        ipLB.setFont(new Font("1", 0, 20));
        ipLB.setBounds(82, 137, 307, 68);
        userPL.add(ipLB);
        JLabel postLB = new JLabel("您的角色是：管理员");
        postLB.setFont(new Font("1", 0, 20));
        postLB.setBounds(82, 234, 307, 68);
        userPL.add(postLB);

    }

}
