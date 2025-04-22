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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketUi extends JFrame {

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

    private int currentPage = 1;
    private int totalPage;
    private String username;
    private String ip;

    ICallerService callerService = new CallerServiceImpl();
    IBusinessWindowService windowService = new BusinessWindowServiceImpl();
    IBusinessTypeService typeService = new BusinessTypeServiceImpl();
    ITicketService ticketService = new TicketServiceImpl();
    private String objectName;
    private JTable table_1;
    private JTable table_2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TicketUi frame = new TicketUi("123", "123");
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
    public TicketUi(String username, String ip) {
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

        JLabel lblNewLabel = new JLabel(new ImageIcon("src/com/yuanhao/pic/icon-home.png"));
        lblNewLabel.setPreferredSize(new Dimension(80, 80));
        topPL.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("\u6392\u961F\u53EB\u53F7\u7BA1\u7406\u7CFB\u7EDF");
        lblNewLabel_1.setFont(new Font("����", Font.BOLD, 40));
        lblNewLabel_1.setPreferredSize(new Dimension(500, 80));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        topPL.add(lblNewLabel_1);

        JPanel bottomPL = new JPanel();
        bottomPL.setMinimumSize(new Dimension(10, 50));
        bottomPL.setPreferredSize(new Dimension(10, 50));
        contentPane.add(bottomPL, BorderLayout.SOUTH);

        JLabel bottomNameLB = new JLabel("@XX�㶫���޹�˾");
        bottomNameLB.setPreferredSize(new Dimension(900, 20));
        bottomNameLB.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPL.add(bottomNameLB);

        JLabel timeLB = new JLabel("ʱ��XXX");
        timeLB.setPreferredSize(new Dimension(900, 20));
        timeLB.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPL.add(timeLB);

        JSplitPane splitPane = new JSplitPane();
        contentPane.add(splitPane, BorderLayout.CENTER);

        JPanel leftPL = new JPanel();
        leftPL.setBorder(new EmptyBorder(5, 5, 5, 5));
        leftPL.setMinimumSize(new Dimension(200, 10));
        leftPL.setPreferredSize(new Dimension(200, 10));
        splitPane.setLeftComponent(leftPL);
        leftPL.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JButton callerBT = new JButton("\u67DC\u5458\u7BA1\u7406", new ImageIcon("src/com/yuanhao/pic/icon-guiyuan.png"));
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
//	                    panelMaker(callerPL, objectName);
                    rightP.addTab("\u67DC\u5458\u754C\u9762", null, callerPL, null);
                    callerPL.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

                }
                int index = rightP.indexOfTab("\u67DC\u5458\u754C\u9762");
                rightP.setSelectedIndex(index);
            }
        });
        leftPL.add(callerBT);

        JButton windowBT = new JButton("\u7A97\u53E3\u7BA1\u7406", new ImageIcon("src/com/yuanhao/pic/icon-chuangkou.png"));
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
//	                    panelMaker(windowPL, objectName);
                    rightP.addTab("\u7A97\u53E3\u7BA1\u7406", null, windowPL, null);
                    windowPL.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

                }
                int index = rightP.indexOfTab("\u7A97\u53E3\u7BA1\u7406");
                rightP.setSelectedIndex(index);

            }
        });
        leftPL.add(windowBT);

        JButton typeBT = new JButton("\u4E1A\u52A1\u7BA1\u7406", new ImageIcon("src/com/yuanhao/pic/icon-yewu.png"));
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

        JButton ticketBT = new JButton("\u529E\u7406\u60C5\u51B5\u67E5\u8BE2", new ImageIcon("src/com/yuanhao/pic/icon-chakan.png"));
        ticketBT.setPreferredSize(new Dimension(190, 50));
        ticketBT.setMaximumSize(new Dimension(190, 40));
        ticketBT.setMinimumSize(new Dimension(190, 40));
        leftPL.add(ticketBT);
        this.rightP = new JTabbedPane(1);
        splitPane.setRightComponent(this.rightP);

        JPanel userPL233 = new JPanel();
        userPL233.setPreferredSize(new Dimension(1200, 400));
        this.rightP.addTab("��ӭ����", (Icon) null, userPL233, (String) null);
        userPL233.setLayout((LayoutManager) null);
        userPL233.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        panel.setPreferredSize(new Dimension(10, 40));
        userPL233.add(panel, BorderLayout.NORTH);

        JButton ByYearBT = new JButton("\u6309\u5E74\u67E5\u770B");
        ByYearBT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        panel.add(ByYearBT);

        JButton ByMonthBT = new JButton("\u6309\u6708\u67E5\u770B");
        panel.add(ByMonthBT);

        JButton ByTimeBT = new JButton("\u6309\u65F6\u95F4\u6BB5\u67E5\u770B");
        panel.add(ByTimeBT);

        JButton ByTodayBT = new JButton("\u6309\u5F53\u5929\u6D41\u91CF\u67E5\u770B");
        panel.add(ByTodayBT);

        JSplitPane ticketSP = new JSplitPane();
        userPL233.add(ticketSP, BorderLayout.CENTER);
        ticketSP.setDividerLocation(0.5);

        JPanel leftpanel_1 = new JPanel();
        ticketSP.setLeftComponent(leftpanel_1);
        leftpanel_1.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        leftpanel_1.add(scrollPane, BorderLayout.CENTER);

        table_1 = new JTable();
        table_1.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                },
                new String[]{
                        "New column", "New column", "New column", "New column", "New column", "New column"
                }
        ));
        scrollPane.setViewportView(table_1);

        JPanel panel_1 = new JPanel();
        leftpanel_1.add(panel_1, BorderLayout.SOUTH);

        JLabel lblNewLabel_2 = new JLabel("New label");
        leftpanel_1.add(lblNewLabel_2, BorderLayout.NORTH);

        JPanel rightpanel_1 = new JPanel();
        ticketSP.setRightComponent(rightpanel_1);
        rightpanel_1.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane_1 = new JScrollPane();
        rightpanel_1.add(scrollPane_1);

        table_2 = new JTable();
        table_2.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                },
                new String[]{
                        "New column", "New column", "New column", "New column", "New column", "New column", "New column"
                }
        ));
        scrollPane_1.setViewportView(table_2);

        JLabel lblNewLabel_3 = new JLabel("New label");
        rightpanel_1.add(lblNewLabel_3, BorderLayout.NORTH);

    }

}
