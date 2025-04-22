package com.yuanhao.manager.ui;

import com.yuanhao.manager.dao.entity.BusinessType;
import com.yuanhao.manager.dao.entity.BusinessWindow;
import com.yuanhao.manager.service.IBusinessTypeService;
import com.yuanhao.manager.service.IBusinessWindowService;
import com.yuanhao.manager.service.impl.BusinessTypeServiceImpl;
import com.yuanhao.manager.service.impl.BusinessWindowServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class WindowDialogUi extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField noTF;
    private JComboBox codeCB;
    private String method;
    private String no;
    IBusinessWindowService windowService = new BusinessWindowServiceImpl();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            WindowDialogUi dialog = new WindowDialogUi("add", "0");
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public WindowDialogUi(String method, String no) {
        setMinimumSize(new Dimension(400, 200));
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(40, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        {
            JPanel panel = new JPanel();
            FlowLayout flowLayout = (FlowLayout) panel.getLayout();
            panel.setPreferredSize(new Dimension(350, 40));
            contentPanel.add(panel);
            {
                JLabel label = new JLabel("\u7A97\u53E3\u53F7\uFF1A");
                label.setPreferredSize(new Dimension(100, 30));
                label.setHorizontalAlignment(SwingConstants.RIGHT);
                label.setFont(new Font("Dialog", Font.PLAIN, 12));
                panel.add(label);
            }
            {
                noTF = new JTextField();
                noTF.setColumns(20);
                panel.add(noTF);
            }
        }
        {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(350, 40));
            contentPanel.add(panel);
            {
                JLabel label = new JLabel("\u529E\u7406\u4E1A\u52A1\uFF1A");
                label.setPreferredSize(new Dimension(100, 30));
                label.setHorizontalAlignment(SwingConstants.RIGHT);
                label.setFont(new Font("Dialog", Font.PLAIN, 12));
                panel.add(label);
            }
            {
                codeCB = new JComboBox();
                IBusinessTypeService typeService = new BusinessTypeServiceImpl();
                List<BusinessType> typeList = typeService.typeList();
                String[] code = new String[typeList.size()];
                for (int i = 0; i < typeList.size(); i++) {
                    code[i] = typeList.get(i).getBusinessTypeCode() + "|" + typeList.get(i).getBusinessTypeName();
                }
                codeCB.setModel(new DefaultComboBoxModel(code));
                codeCB.setPreferredSize(new Dimension(120, 21));
                panel.add(codeCB);
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
    private BusinessWindow getMSG() {
        BusinessWindow bw = new BusinessWindow();
        String item = (String) codeCB.getSelectedItem();
        String[] message = item.split("\\|");
        bw.setBwNo(noTF.getText());
        bw.setBwTypeCode(message[0]);
        bw.setBwTypeName(message[1]);
        return bw;

    }

    /**
     * 修改时自动填写所选行信息
     *
     * @param no 所选行数的编号
     */
    private void setMSG(String no) {
        BusinessWindow bw = windowService.businessWindowGetByNo(no);
        noTF.setText(bw.getBwNo());
        String item = bw.getBwTypeCode() + "|" + bw.getBwTypeName();
        codeCB.setSelectedItem(item);
    }

    /**
     * 功能选择
     *
     * @param method 所选功能名
     * @param no     所选行数对象编号
     */
    private void doMethod(String method, String no) {
        if (method.equals("add")) {
            BusinessWindow bw = getMSG();
            if (bw == null) {
                JOptionPane.showConfirmDialog(null, "那我问你，你脑袋是不是尖尖的");
            } else {
                int flag = windowService.businessWindowAdd(bw);
                if (flag < 1) {
                    JOptionPane.showConfirmDialog(null, "传参错误！！参数为：" + flag);
                } else {
                    JOptionPane.showConfirmDialog(null, "添加成功");
                    this.dispose();
                }
            }
        } else if (method.equals("update")) {
            BusinessWindow bw = getMSG();
            int flag = windowService.businessWindowUpdate(no, bw);
            if (flag < 1) {
                JOptionPane.showConfirmDialog(null, "传参错误！！参数为：" + flag);
            } else {
                JOptionPane.showConfirmDialog(null, "修改成功");
                this.dispose();
            }
        } else {
            JOptionPane.showConfirmDialog(null, "传参错误！！参数为：" + method);
        }
    }

    /**
     * 重置弹窗信息
     */
    private void reset() {
        noTF.setText("");
        codeCB.setSelectedIndex(1);
    }

}
