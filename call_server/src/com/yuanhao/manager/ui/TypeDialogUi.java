package com.yuanhao.manager.ui;

import com.yuanhao.manager.dao.entity.BusinessType;
import com.yuanhao.manager.service.IBusinessTypeService;
import com.yuanhao.manager.service.impl.BusinessTypeServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TypeDialogUi extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField descTF;
    private JTextField codeTF;
    private JTextField nameTF;
    private JTextField limitTF;
    private String method;
    private String no;
    IBusinessTypeService typeService = new BusinessTypeServiceImpl();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            TypeDialogUi dialog = new TypeDialogUi("add", "0");
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public TypeDialogUi(String method, String no) {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(15, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(350, 40));
            contentPanel.add(panel);
            {
                JLabel label = new JLabel("\u4E1A\u52A1\u4EE3\u53F7\uFF1A");
                label.setPreferredSize(new Dimension(100, 30));
                label.setHorizontalAlignment(SwingConstants.RIGHT);
                label.setFont(new Font("Dialog", Font.PLAIN, 12));
                panel.add(label);
            }
            {
                codeTF = new JTextField();
                codeTF.setColumns(20);
                panel.add(codeTF);
            }
        }
        {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(350, 40));
            contentPanel.add(panel);
            {
                JLabel label = new JLabel("\u4E1A\u52A1\u540D\u79F0\uFF1A");
                label.setPreferredSize(new Dimension(100, 30));
                label.setHorizontalAlignment(SwingConstants.RIGHT);
                label.setFont(new Font("Dialog", Font.PLAIN, 12));
                panel.add(label);
            }
            {
                nameTF = new JTextField();
                nameTF.setColumns(20);
                panel.add(nameTF);
            }
        }
        {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(350, 40));
            contentPanel.add(panel);
            {
                JLabel label = new JLabel("\u5F53\u5929\u4E0A\u9650\uFF1A");
                label.setPreferredSize(new Dimension(100, 30));
                label.setHorizontalAlignment(SwingConstants.RIGHT);
                label.setFont(new Font("Dialog", Font.PLAIN, 12));
                panel.add(label);
            }
            {
                limitTF = new JTextField();
                limitTF.setColumns(20);
                panel.add(limitTF);
            }
        }
        {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(350, 40));
            contentPanel.add(panel);
            {
                JLabel label = new JLabel("\u4E1A\u52A1\u5907\u6CE8\uFF1A");
                label.setPreferredSize(new Dimension(100, 30));
                label.setHorizontalAlignment(SwingConstants.RIGHT);
                label.setFont(new Font("Dialog", Font.PLAIN, 12));
                panel.add(label);
            }
            {
                descTF = new JTextField();
                descTF.setColumns(20);
                panel.add(descTF);
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("\u786E\u8BA4");
                okButton.setActionCommand("OK");
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        doMethod(method, no);
                    }
                });
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("\u91CD\u7F6E");
                cancelButton.setActionCommand("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (method.equals("update")) {
                            setMSG(no);
                        } else {
                            reset();
                        }
                    }
                });
                buttonPane.add(cancelButton);
            }
        }
        if (method.equals("update")) {
            setMSG(no);
        }
    }

    /**
     * 获取弹窗信息
     *
     * @return 信息
     */
    private BusinessType getMSG() {
        BusinessType businessType = new BusinessType();
        businessType.setBusinessTypeCode(codeTF.getText());
        businessType.setBusinessTypeName(nameTF.getText());
        businessType.setBusinessTypeLimitCount(Integer.parseInt(limitTF.getText()));
        businessType.setBusinessTypeDesc(descTF.getText());
        return businessType;
    }

    /**
     * 修改时自动填写所选行信息
     *
     * @param no 所选行数的编号
     */
    private void setMSG(String no) {
        BusinessType businessType = typeService.businessTypeGetByNo(no);
        codeTF.setText(businessType.getBusinessTypeCode());
        nameTF.setText(businessType.getBusinessTypeName());
        limitTF.setText(String.valueOf(businessType.getBusinessTypeLimitCount()));
        descTF.setText(businessType.getBusinessTypeDesc());
    }

    /**
     * 功能选择
     *
     * @param method 所选功能名
     * @param no     所选行数对象编号
     */
    private void doMethod(String method, String no) {
        if (method.equals("add")) {
            BusinessType businessType = getMSG();
            if (businessType == null) {
                JOptionPane.showConfirmDialog(null, "那我问你，你脑袋是不是尖尖的");
            } else {
                int flag = typeService.businessTypeAdd(businessType);
                if (flag < 1) {
                    JOptionPane.showConfirmDialog(null, "添加失败！！参数为：" + flag);
                } else {
                    JOptionPane.showConfirmDialog(null, "添加成功");
                    this.dispose();
                }
            }
        } else if (method.equals("update")) {
            BusinessType businessType = getMSG();
            int flag = typeService.businessTypeUpdate(no, businessType);
            if (flag < 1) {
                JOptionPane.showConfirmDialog(null, "修改失败！！参数为：" + flag);
            } else {
                JOptionPane.showConfirmDialog(null, "修改成功");
                this.dispose();
            }
        } else {
            JOptionPane.showConfirmDialog(null, "操作错误！！参数为：" + method);
        }
    }

    /**
     * 重置弹窗信息
     */
    private void reset() {
        codeTF.setText("");
        nameTF.setText("");
        limitTF.setText("");
        descTF.setText("");
    }

}
