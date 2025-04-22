package com.yuanhao.manager.ui;

import com.yuanhao.manager.dao.entity.Caller;
import com.yuanhao.manager.service.ICallerService;
import com.yuanhao.manager.service.impl.CallerServiceImpl;
import com.yuanhao.utils.DateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class CallerDialogUi extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    DateChooser dataChooser1 = DateChooser.getInstance("yyyy-MM-dd");
    DateChooser dataChooser2 = DateChooser.getInstance("yyyy-MM-dd");
    DateChooser dataChooser3 = DateChooser.getInstance("yyyy-MM-dd");
    private JTextField textField_6;
    private JTextField textField_7;
    private ButtonGroup group;
    private JRadioButton rdbtnNewRadioButton;
    private JRadioButton rdbtnNewRadioButton_1;
    ICallerService callerService = new CallerServiceImpl();
    private String method;
    private String no;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            CallerDialogUi callerdialog = new CallerDialogUi("add", "");
            callerdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            callerdialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public CallerDialogUi(String method, String no) {
        setMinimumSize(new Dimension(200, 500));
        setSize(new Dimension(200, 600));
        setPreferredSize(new Dimension(200, 600));
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setFont(new Font("����", Font.PLAIN, 14));
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(350, 40));
            contentPanel.add(panel);
            {
                JLabel lblNewLabel = new JLabel("\u67DC\u5458\u5DE5\u53F7\uFF1A");
                lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                lblNewLabel.setFont(new Font("����", Font.PLAIN, 12));
                lblNewLabel.setPreferredSize(new Dimension(100, 30));
                panel.add(lblNewLabel);
            }
            {
                textField = new JTextField();
                panel.add(textField);
                textField.setColumns(20);
            }
        }
        {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(350, 40));
            contentPanel.add(panel);
            {
                JLabel label = new JLabel("\u67DC\u5458\u59D3\u540D\uFF1A");
                label.setPreferredSize(new Dimension(100, 30));
                label.setHorizontalAlignment(SwingConstants.RIGHT);
                label.setFont(new Font("����", Font.PLAIN, 12));
                panel.add(label);
            }
            {
                textField_1 = new JTextField();
                textField_1.setColumns(20);
                panel.add(textField_1);
            }
        }
        {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(350, 40));
            contentPanel.add(panel);
            {
                JLabel label = new JLabel("\u67DC\u5458\u5BC6\u7801\uFF1A");
                label.setPreferredSize(new Dimension(100, 30));
                label.setHorizontalAlignment(SwingConstants.RIGHT);
                label.setFont(new Font("Dialog", Font.PLAIN, 12));
                panel.add(label);
            }
            {
                textField_6 = new JPasswordField();
                textField_6.setColumns(20);
                panel.add(textField_6);
            }
        }
        {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(350, 40));
            contentPanel.add(panel);
            {
                JLabel label = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
                label.setPreferredSize(new Dimension(100, 30));
                label.setHorizontalAlignment(SwingConstants.RIGHT);
                label.setFont(new Font("Dialog", Font.PLAIN, 12));
                panel.add(label);
            }
            {
                textField_7 = new JPasswordField();
                textField_7.setColumns(20);
                panel.add(textField_7);
            }
        }
        {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(350, 40));
            contentPanel.add(panel);
            {
                JLabel label = new JLabel("\u67DC\u5458\u6027\u522B\uFF1A");
                label.setPreferredSize(new Dimension(100, 30));
                label.setHorizontalAlignment(SwingConstants.RIGHT);
                label.setFont(new Font("����", Font.PLAIN, 12));
                panel.add(label);
            }

            {
                rdbtnNewRadioButton = new JRadioButton("\u7537");
                panel.add(rdbtnNewRadioButton);
            }

            {
                rdbtnNewRadioButton_1 = new JRadioButton("\u5973");
                rdbtnNewRadioButton_1.setMargin(new Insets(2, 50, 2, 2));
                panel.add(rdbtnNewRadioButton_1);
            }
            group = new ButtonGroup();
            group.add(rdbtnNewRadioButton);
            group.add(rdbtnNewRadioButton_1);
        }
        {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(350, 40));
            contentPanel.add(panel);
            {
                JLabel label = new JLabel("\u5165\u804C\u65F6\u95F4\uFF1A");
                label.setPreferredSize(new Dimension(100, 30));
                label.setHorizontalAlignment(SwingConstants.RIGHT);
                label.setFont(new Font("����", Font.PLAIN, 12));
                panel.add(label);
            }
            {
                textField_2 = new JTextField();
                textField_2.setColumns(20);
                dataChooser3.register(textField_2);
                panel.add(textField_2);
            }
        }
        {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(350, 40));
            contentPanel.add(panel);
            {
                JLabel label = new JLabel("\u51FA\u751F\u65E5\u671F\uFF1A");
                label.setPreferredSize(new Dimension(100, 30));
                label.setHorizontalAlignment(SwingConstants.RIGHT);
                label.setFont(new Font("����", Font.PLAIN, 12));
                panel.add(label);
            }
            {
                textField_3 = new JTextField();
                textField_3.setColumns(20);
                dataChooser1.register(textField_3);
                panel.add(textField_3);
            }
        }
        {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(350, 40));
            contentPanel.add(panel);
            {
                JLabel label = new JLabel("\u6700\u540E\u767B\u5F55\u65F6\u95F4\uFF1A");
                label.setPreferredSize(new Dimension(100, 30));
                label.setHorizontalAlignment(SwingConstants.RIGHT);
                label.setFont(new Font("����", Font.PLAIN, 12));
                panel.add(label);
            }
            {
                textField_4 = new JTextField();
                textField_4.setColumns(20);
                dataChooser2.register(textField_4);
                dataChooser2.setEnabled(false);
                panel.add(textField_4);
            }
        }
        {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(350, 40));
            contentPanel.add(panel);
            {
                JLabel lblip = new JLabel("\u6700\u540E\u767B\u5F55IP\uFF1A");
                lblip.setPreferredSize(new Dimension(100, 30));
                lblip.setHorizontalAlignment(SwingConstants.RIGHT);
                lblip.setFont(new Font("����", Font.PLAIN, 12));
                panel.add(lblip);
            }
            {
                textField_5 = new JTextField();
                textField_5.setColumns(20);
                panel.add(textField_5);
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okBT = new JButton("\u786E\u8BA4");
                okBT.setActionCommand("OK");
                okBT.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        doMethod(method, no);
                    }
                });
                buttonPane.add(okBT);
                getRootPane().setDefaultButton(okBT);
            }
            {
                JButton resetBT = new JButton("\u91CD\u7F6E");
                resetBT.setActionCommand("Cancel");
                resetBT.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (method.equals("update")) {
                            setMSG(no);
                        }else {
                            reset();
                        }
                    }
                });
                buttonPane.add(resetBT);
            }
        }
        if (method.equals("update")) {
            setMSG(no);
        }
    }
    /**
     * 获取弹窗信息
     * @return 信息
     */
    private Caller getMSG() {
        Caller caller = null;
        String c_workno = textField.getText();
        String c_name = textField_1.getText();
        String c_password = textField_6.getText();
        String re_password = textField_7.getText();
        if (!c_password.equals(re_password) ) {
            JOptionPane.showConfirmDialog(null, "密码不一致");
        } else {
            String c_sex;
            if (rdbtnNewRadioButton.isSelected()) {
                c_sex = "男";
            } else if (rdbtnNewRadioButton_1.isSelected()) {
                c_sex = "女";
            } else {
                c_sex = "武装直升机";
            }
            Date c_hireDate = dataChooser3.getDate();
            Date c_birth = dataChooser1.getDate();
            Date c_last_login_time = new Date();
            String c_last_login_ip = textField_5.getText();
            caller = new Caller(c_workno, c_password, c_name, c_sex, c_hireDate, c_birth, c_last_login_time, c_last_login_ip);
            return caller;
        }
        return caller;
    }
    /**
     * 修改时自动填写所选行信息
     * @param no 所选行数的编号
     */
    private void setMSG(String no) {
        Caller caller = callerService.getByNo(no);
        textField.setText(caller.getCallerWorkNo());
        textField_1.setText(caller.getCallerName());
        textField_6.setText(caller.getCallerPassword());
        textField_7.setText("");
        if (caller.getCallerSex().equals("男")) {
            rdbtnNewRadioButton.setSelected(true);
        } else if (caller.getCallerSex().equals("女")) {
            rdbtnNewRadioButton_1.setSelected(true);
        }
        textField_2.setText(String.valueOf(caller.getCallerHireDate()));
        textField_3.setText(String.valueOf(caller.getCallerBrith()));
        textField_4.setText(String.valueOf(caller.getCallerLastLoginTime()));
        textField_5.setText(caller.getCallerLastLoginIp());
    }
    /**
     * 功能选择
     * @param method 所选功能名
     * @param no 所选行数对象编号
     */
    private void doMethod(String method, String no) {
        if (method.equals("add")) {
            Caller caller = getMSG();
            if (caller == null) {
                JOptionPane.showConfirmDialog(null, "那我问你，你是不是乱数密码了" );
            }
            else {
                int flag = callerService.callerAdd(caller);
                if (flag < 1) {
                    JOptionPane.showConfirmDialog(null, "传参错误！！参数为：" + flag);
                } else {
                    JOptionPane.showConfirmDialog(null, "添加成功");
                    this.dispose();
                }
            }
        } else if (method.equals("update")) {
            Caller caller = getMSG();
            int flag = callerService.callerUpdate(no, caller);
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
    private void reset(){
        textField.setText("");
        textField_1.setText("");
        textField_6.setText("");
        textField_7.setText("");
        rdbtnNewRadioButton.setSelected(false);
        rdbtnNewRadioButton_1.setSelected(false);
        textField_2.setText("");
        textField_3.setText("");
        textField_4.setText(String.valueOf(new Date()));
        textField_5.setText("");
    }

}
