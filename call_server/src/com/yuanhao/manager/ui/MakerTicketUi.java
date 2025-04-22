package com.yuanhao.manager.ui;

import com.yuanhao.manager.dao.entity.BusinessType;
import com.yuanhao.manager.dao.entity.Ticket;
import com.yuanhao.manager.service.ITicketService;
import com.yuanhao.manager.service.impl.TicketServiceImpl;
import com.yuanhao.utils.Page;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MakerTicketUi {
    private JTable table_2;
    private JTable table_1;
    private JButton firstPageBT;
    private JButton lastPageBT;
    private JButton nextPageBT;
    private JButton finalPageBT;
    private JButton pagesBT;
    private JTextField searchTF;
    private JLabel pageInfoLB;
    private JTextField currentPageTF;
    private int currentPage;
    private int totalPage;
    private JPanel object;
    private String objectName;
    ITicketService ticketService = new TicketServiceImpl();
    String[] ticketColumns = new String[]{"票号", "票号业务代码", "票号业务名称", "取票时间", "叫号时间", "取票IP", "叫号IP", "叫号次数", "办理状况"};
    private Page<Ticket> ticketPage;

    public MakerTicketUi(JPanel object, String objectName) {
        object.setLayout((LayoutManager) null);
        object.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        panel.setPreferredSize(new Dimension(10, 40));
        object.add(panel, BorderLayout.NORTH);

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
        object.add(ticketSP, BorderLayout.CENTER);
        ticketSP.setDividerLocation(0.5);

        JPanel leftpanel_1 = new JPanel();
        ticketSP.setLeftComponent(leftpanel_1);
        leftpanel_1.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        leftpanel_1.add(scrollPane, BorderLayout.CENTER);

        table_1 = new JTable();
        table_1.setModel(new DefaultTableModel(
                new Object[][] {
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                },
                new String[] {
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
                new Object[][] {
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                },
                new String[] {
                        "New column", "New column", "New column", "New column", "New column", "New column", "New column"
                }
        ));
        scrollPane_1.setViewportView(table_2);
        //页面跳转功能
        JLabel lblNewLabel_3 = new JLabel("New label");
        rightpanel_1.add(lblNewLabel_3, BorderLayout.NORTH);
        firstPageBT = new JButton("\u9996\u9875");
        firstPageBT.setPreferredSize(new Dimension(60, 30));
        firstPageBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstPage();
            }
        });
        panel_1.add(firstPageBT);

        lastPageBT = new JButton("\u4E0A\u4E00\u9875");
        lastPageBT.setPreferredSize(new Dimension(75, 30));
        lastPageBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lastPage(objectName);
            }
        });
        panel_1.add(lastPageBT);

        nextPageBT = new JButton("\u4E0B\u4E00\u9875");
        nextPageBT.setPreferredSize(new Dimension(75, 30));
        nextPageBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextPage();
            }
        });
        panel_1.add(nextPageBT);

        finalPageBT = new JButton("\u5C3E\u9875");
        finalPageBT.setPreferredSize(new Dimension(60, 30));
        finalPageBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fianlPage();
            }
        });
        panel_1.add(finalPageBT);

        pageInfoLB = new JLabel("\u663E\u793A\u9875\u6570");
        pageInfoLB.setFont(new Font("����", Font.PLAIN, 12));
        pageInfoLB.setPreferredSize(new Dimension(150, 30));
        panel_1.add(pageInfoLB);

        currentPageTF = new JTextField();
        currentPageTF.setPreferredSize(new Dimension(6, 30));
        panel_1.add(currentPageTF);
        currentPageTF.setColumns(6);

        pagesBT = new JButton("\u8DF3\u8F6C");
        pagesBT.setPreferredSize(new Dimension(60, 30));
        pagesBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPageTF.getText() != null) {
                    setObjectTP();
                    if (Integer.parseInt(currentPageTF.getText()) > totalPage) {
                        JOptionPane.showConfirmDialog(null, "请输入有效数字");
                    } else if (Integer.parseInt(currentPageTF.getText()) < 1) {
                        JOptionPane.showConfirmDialog(null, "请输入有效数字");
                    } else {
                        currentPage = Integer.parseInt(currentPageTF.getText());
                        makePage(currentPage);
                    }
                } else {
                    JOptionPane.showConfirmDialog(null, "请输入有效数字");
                }
            }
        });
        panel_1.add(pagesBT);
        currentPage = 1;
        makePage(currentPage);
    }

    private void makePage(int currentPage) {
        int pageSize = 5;
        ticketPage = ticketService.ticketPages("", currentPage, pageSize);
        List<Ticket> ticketList = ticketPage.getList();
        Object[][] data = new Object[ticketList.size()][ticketColumns.length];
        for (int i = 0; i < ticketList.size(); i++) {
            Ticket ticket = ticketList.get(i);
            data[i][0] = ticket.getTicketNo();
            data[i][1] = ticket.getTicketBusinessCode();
            data[i][2] = ticket.getTicketBusinessName();
            data[i][3] = ticket.getTicketTakeTime();
            data[i][4] = ticket.getTicketCallTime();
            data[i][5] = ticket.getTicketTakeIp();
            data[i][6] = ticket.getTicketCallIp();
            data[i][7] = ticket.getTicketCallCount();
            data[i][8] = ticket.getTicketIsSuccess();
        }
        table_1.setModel(new DefaultTableModel(data, ticketColumns));
        String pageMSG = "当前第" + ticketPage.getCurrentPage() + "页，总计" + ticketPage.getCount() + "条数据";
        pageInfoLB.setText(pageMSG);

    }

    private void firstPage() {
        currentPage = 1;
        makePage( currentPage);
    }

    private void nextPage() {
        currentPage = currentPage + 1;
        setObjectTP();
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        makePage(currentPage);
    }

    private void lastPage(String objectName) {
        currentPage = currentPage - 1;
        if (currentPage < 1) {
            currentPage = 1;
        }
        makePage(currentPage);
    }

    private void fianlPage() {
        setObjectTP();
        makePage(totalPage);
        currentPage = totalPage;
    }

    private void setObjectTP() {
            totalPage = ticketPage.getTotalPages();
    }
}
