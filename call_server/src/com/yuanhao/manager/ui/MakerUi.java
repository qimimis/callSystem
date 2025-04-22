package com.yuanhao.manager.ui;

import com.yuanhao.manager.dao.entity.BusinessType;
import com.yuanhao.manager.dao.entity.BusinessWindow;
import com.yuanhao.manager.dao.entity.Caller;
import com.yuanhao.manager.dao.entity.Ticket;
import com.yuanhao.manager.service.IBusinessTypeService;
import com.yuanhao.manager.service.IBusinessWindowService;
import com.yuanhao.manager.service.ICallerService;
import com.yuanhao.manager.service.ITicketService;
import com.yuanhao.manager.service.impl.BusinessTypeServiceImpl;
import com.yuanhao.manager.service.impl.BusinessWindowServiceImpl;
import com.yuanhao.manager.service.impl.CallerServiceImpl;
import com.yuanhao.manager.service.impl.TicketServiceImpl;
import com.yuanhao.utils.Page;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class MakerUi {
    private JScrollPane jScrollPane;
    private JPanel panel_2;
    private JButton updateBT;
    private JButton pagesBT;
    private JButton finalPageBT;
    private JButton nextPageBT;
    private JPanel topPL;
    private JPanel methodPL;
    private JButton addBT;
    private JButton deleteBT;
    private JButton refreshBT;
    private JButton firstPageBT;
    private JButton lastPageBT;
    private JTextField searchTF;
    private JLabel pageInfoLB;
    private JTextField currentPageTF;
    private int currentPage;
    private JTable table;
    private int totalPage;
    private String objectName;
    String[] callerColumns = new String[]{"柜员工号", "柜员姓名", "柜员性别", "入职日期", "出生日期", "最后登录日期", "最后登录IP"};
    String[] windowColumns = new String[]{"窗口号", "办理业务代号", "办理业务名称"};
    String[] typeColumns = new String[]{"业务代号", "业务名称", "当天上限", "业务备注"};

    ICallerService callerService = new CallerServiceImpl();
    IBusinessWindowService windowService = new BusinessWindowServiceImpl();
    IBusinessTypeService typeService = new BusinessTypeServiceImpl();


    private Page<Caller> callerPage;
    private Page<BusinessWindow> windowPage;
    private Page<BusinessType> typePage;

    /**
     * 构造主页
     * @param object 构造的对象
     * @param objectName 所选对象名称
     */
    public MakerUi(JPanel object, String objectName) {

        object.setLayout(new FlowLayout(1, 5, 5));
        topPL = new JPanel();
        FlowLayout fl_callerPL1 = (FlowLayout) topPL.getLayout();
        fl_callerPL1.setAlignment(0);
        topPL.setPreferredSize(new Dimension(1050, 40));
        object.add(topPL);
        searchTF = new JTextField();
        searchTF.setPreferredSize(new Dimension(6, 30));
        topPL.add(searchTF);
        searchTF.setColumns(30);
        JButton searchBT = new JButton("搜索");
        searchBT.setPreferredSize(new Dimension(90, 30));
        searchBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makePage(objectName, 1);
            }
        });
        topPL.add(searchBT);

        methodPL = new JPanel();
        FlowLayout fl_callerPL2 = (FlowLayout) methodPL.getLayout();
        fl_callerPL2.setAlignment(FlowLayout.RIGHT);
        methodPL.setPreferredSize(new Dimension(1050, 40));
        object.add(methodPL);
        //增删改查功能
        addBT = new JButton("\u6DFB\u52A0");
        addBT.setPreferredSize(new Dimension(90, 30));
        addBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addInfo(objectName);
                currentPage = 1;
                makePage(objectName,currentPage);
            }
        });
        methodPL.add(addBT);

        deleteBT = new JButton("\u5220\u9664");
        deleteBT.setPreferredSize(new Dimension(90, 30));
        deleteBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] rows = table.getSelectedRows();
                String[] nos = new String[rows.length];
                for (int i = 0; i < rows.length; i++) {
                    nos[i] = (String) table.getValueAt(rows[i], 0);
                }
                if (rows.length < 1) {
                    JOptionPane.showConfirmDialog(null, "选中行数不得少于1");
                } else {
                    deleteInfo(objectName, nos);
                }
                currentPage = 1;
                makePage(objectName,currentPage);
            }
        });
        methodPL.add(deleteBT);

        updateBT = new JButton("\u4FEE\u6539");
        updateBT.setPreferredSize(new Dimension(90, 30));
        updateBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] rows = table.getSelectedRows();
                String no = null;
                if (rows.length < 1) {
                    JOptionPane.showConfirmDialog(null, "选中行数不得少于1");
                } else if (rows.length > 1) {
                    JOptionPane.showConfirmDialog(null, "选中行数不得多于1");
                } else {
                    Object value = table.getValueAt(rows[0], 0);
                    no = (String) value;
                    updateInfo(objectName, no);
                    currentPage = 1;
                    makePage(objectName,currentPage);
                }

            }
        });
        methodPL.add(updateBT);
        //刷新表信息
        refreshBT = new JButton("\u5237\u65B0");
        refreshBT.setPreferredSize(new Dimension(90, 30));
        refreshBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchTF.setText("");
                currentPage = 1;
                makePage(objectName,currentPage);
            }
        });
        methodPL.add(refreshBT, objectName);

        jScrollPane = new JScrollPane();
        jScrollPane.setPreferredSize(new Dimension(1050, 320));
        object.add(jScrollPane);

        table = new JTable();

        jScrollPane.setViewportView(table);

        panel_2 = new JPanel();
        panel_2.setPreferredSize(new Dimension(1050, 40));
        object.add(panel_2);
        //页码按钮
        firstPageBT = new JButton("\u9996\u9875");
        firstPageBT.setPreferredSize(new Dimension(90, 30));
        firstPageBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstPage(objectName);
            }
        });
        panel_2.add(firstPageBT);

        lastPageBT = new JButton("\u4E0A\u4E00\u9875");
        lastPageBT.setPreferredSize(new Dimension(99, 30));
        lastPageBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lastPage(objectName);
            }
        });
        panel_2.add(lastPageBT);

        nextPageBT = new JButton("\u4E0B\u4E00\u9875");
        nextPageBT.setPreferredSize(new Dimension(90, 30));
        nextPageBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextPage(objectName);
            }
        });
        panel_2.add(nextPageBT);

        finalPageBT = new JButton("\u5C3E\u9875");
        finalPageBT.setPreferredSize(new Dimension(90, 30));
        finalPageBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fianlPage(objectName);
            }
        });
        panel_2.add(finalPageBT);

        pageInfoLB = new JLabel("\u663E\u793A\u9875\u6570");
        pageInfoLB.setFont(new Font("����", Font.PLAIN, 12));
        pageInfoLB.setPreferredSize(new Dimension(200, 30));
        panel_2.add(pageInfoLB);

        currentPageTF = new JTextField();
        currentPageTF.setPreferredSize(new Dimension(6, 30));
        panel_2.add(currentPageTF);
        currentPageTF.setColumns(6);

        pagesBT = new JButton("\u8DF3\u8F6C");
        pagesBT.setPreferredSize(new Dimension(90, 30));
        pagesBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPageTF.getText() != null) {
                    setObjectTP(objectName);
                    if (Integer.parseInt(currentPageTF.getText()) > totalPage) {
                        JOptionPane.showConfirmDialog(null, "请输入有效数字");
                    } else if (Integer.parseInt(currentPageTF.getText()) < 1) {
                        JOptionPane.showConfirmDialog(null, "请输入有效数字");
                    } else {
                        currentPage = Integer.parseInt(currentPageTF.getText());
                        makePage(objectName, currentPage);
                    }
                } else {
                    JOptionPane.showConfirmDialog(null, "请输入有效数字");
                }
            }
        });
        panel_2.add(pagesBT);
        currentPage = 1;
        makePage(objectName,currentPage);
    }

    /**
     * 查
     * @param objectName 当前主页名称
     * @param currentPage 当前页数
     */
    private void makePage(String objectName, int currentPage) {
        String condition = searchTF.getText();
        int pageSize = 5;
        if (objectName.equals("caller")) {
            callerPage = callerService.callerPages(condition, currentPage, pageSize);
            java.util.List<Caller> callerList = callerPage.getList();
            Object[][] data = new Object[callerList.size()][callerColumns.length];
            for (int i = 0; i < callerList.size(); i++) {
                Caller caller = callerList.get(i);
                data[i][0] = caller.getCallerWorkNo();
                data[i][1] = caller.getCallerName();
                data[i][2] = caller.getCallerSex();
                data[i][3] = caller.getCallerHireDate();
                data[i][4] = caller.getCallerBrith();
                data[i][5] = caller.getCallerLastLoginTime();
                data[i][6] = caller.getCallerLastLoginIp();
            }
            table.setModel(new DefaultTableModel(data, callerColumns));
            String pageMSG = "当前第" + callerPage.getCurrentPage() + "页，总计" + callerPage.getCount() + "条数据";
            pageInfoLB.setText(pageMSG);
        } else if (objectName.equals("window")) {
            windowPage = windowService.businessWindowPages(condition, currentPage, pageSize);
            java.util.List<BusinessWindow> windowList = windowPage.getList();
            Object[][] data = new Object[windowList.size()][windowColumns.length];
            for (int i = 0; i < windowList.size(); i++) {
                BusinessWindow window = windowList.get(i);
                data[i][0] = window.getBwNo();
                data[i][1] = window.getBwTypeCode();
                data[i][2] = window.getBwTypeName();
            }
            table.setModel(new DefaultTableModel(data, windowColumns));
            String pageMSG = "当前第" + windowPage.getCurrentPage() + "页，总计" + windowPage.getCount() + "条数据";
            pageInfoLB.setText(pageMSG);

        } else if (objectName.equals("type")) {
            typePage = typeService.businessTypePages(condition, currentPage, pageSize);
            java.util.List<BusinessType> typeList = typePage.getList();
            Object[][] data = new Object[typeList.size()][typeColumns.length];
            for (int i = 0; i < typeList.size(); i++) {
                BusinessType type = typeList.get(i);
                data[i][0] = type.getBusinessTypeCode();
                data[i][1] = type.getBusinessTypeName();
                data[i][2] = type.getBusinessTypeLimitCount();
                data[i][3] = type.getBusinessTypeDesc();
            }
            table.setModel(new DefaultTableModel(data, typeColumns));
            String pageMSG = "当前第" + typePage.getCurrentPage() + "页，总计" + typePage.getCount() + "条数据";
            pageInfoLB.setText(pageMSG);
        }
    }

    private void firstPage(String objectName) {
        currentPage = 1;
        makePage(objectName, currentPage);
    }

    private void nextPage(String objectName) {
        currentPage = currentPage + 1;
        setObjectTP(objectName);
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        makePage(objectName, currentPage);
    }

    private void lastPage(String objectName) {
        currentPage = currentPage - 1;
        if (currentPage < 1) {
            currentPage = 1;
        }
        makePage(objectName, currentPage);
    }

    private void fianlPage(String objectName) {
        setObjectTP(objectName);
        makePage(objectName, totalPage);
        currentPage = totalPage;
    }

    /**
     * 初始化获取各对象的总页数
     * @param objectName 当前主页名称
     */
    private void setObjectTP(String objectName) {
        if (objectName.equals("caller")) {
            totalPage = callerPage.getTotalPages();
        } else if (objectName.equals("window")) {
            totalPage = windowPage.getTotalPages();
        } else if (objectName.equals("type")) {
            totalPage = typePage.getTotalPages();
        }
    }
    /**
     * 增
     * @param objectName 当前主页名称
     */
    private void addInfo(String objectName) {
        if (objectName.equals("caller")) {
            CallerDialogUi callerdialog = new CallerDialogUi("add", "-1");
            callerdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            callerdialog.setVisible(true);
        } else if (objectName.equals("window")) {
            WindowDialogUi windowDialogUi = new WindowDialogUi("add", "-1");
            windowDialogUi.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            windowDialogUi.setVisible(true);
        } else if (objectName.equals("type")) {
            TypeDialogUi typeDialogUi = new TypeDialogUi("add", "-1");
            typeDialogUi.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            typeDialogUi.setVisible(true);
        }
    }
    /**
     * 改
     * @param objectName 当前主页名称
     * @param no 所选对象行
     */
    private void updateInfo(String objectName, String no) {
        if (objectName.equals("caller")) {
            CallerDialogUi callerdialog = new CallerDialogUi("update", no);
            callerdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            callerdialog.setVisible(true);
        } else if (objectName.equals("window")) {
            WindowDialogUi windowDialogUi = new WindowDialogUi("update", no);
            windowDialogUi.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            windowDialogUi.setVisible(true);
        } else if (objectName.equals("type")) {
            TypeDialogUi typeDialogUi = new TypeDialogUi("update", no);
            typeDialogUi.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            typeDialogUi.setVisible(true);
        }
    }
    /**
     * 删
     * @param objectName 当前主页名称
     * @param no 所选对象行
     */
    private void deleteInfo(String objectName, String... nos) {
        if (objectName.equals("caller")) {
            int flag = callerService.callerDelete(nos);
            if (flag == 1) {
                JOptionPane.showConfirmDialog(null, "删除成功");
            } else {
                JOptionPane.showConfirmDialog(null, "删除失败");
            }
        } else if (objectName.equals("window")) {
            int flag = windowService.businessWindowDelete(nos);
            if (flag == 1) {
                JOptionPane.showConfirmDialog(null, "删除成功");
            } else {
                JOptionPane.showConfirmDialog(null, "删除失败");
            }
        } else if (objectName.equals("type")) {
            int flag = typeService.businessTypeDelete(nos);
            if (flag == 1) {
                JOptionPane.showConfirmDialog(null, "删除成功");
            } else {
                JOptionPane.showConfirmDialog(null, "删除失败");
            }
        }
    }


}
