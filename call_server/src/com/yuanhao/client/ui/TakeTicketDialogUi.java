package com.yuanhao.client.ui;

import com.yuanhao.manager.dao.entity.Ticket;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class TakeTicketDialogUi extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TakeTicketDialogUi dialog = new TakeTicketDialogUi(new Ticket());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//取票后弹窗
	/**
	 * Create the dialog.
	 */
	public TakeTicketDialogUi(Ticket ticket) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel imageLB = new JLabel("image here");
		imageLB.setBounds(28, 64, 82, 84);
		contentPanel.add(imageLB);
		
		JLabel LB1 = new JLabel("您的票号信息："+ticket.getTicketId());
		LB1.setBounds(120, 24, 221, 31);
		contentPanel.add(LB1);
		
		JLabel LB2 = new JLabel("业务类型："+ticket.getTicketBusinessCode());
		LB2.setBounds(120, 64, 221, 31);
		contentPanel.add(LB2);
		
		JLabel LB3 = new JLabel("票号："+ticket.getTicketNo());
		LB3.setBounds(120, 105, 221, 31);
		contentPanel.add(LB3);

		JLabel LB4 = new JLabel("等待人数："+ticket.getTicketWaitCount());
		LB4.setBounds(120, 146, 221, 31);
		contentPanel.add(LB4);
		
		JLabel LB5 = new JLabel("领票时间："+ticket.getTicketTakeTime());
		LB5.setBounds(120, 187, 221, 31);
		contentPanel.add(LB5);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setPreferredSize(new Dimension(100, 23));
				okButton.setHorizontalTextPosition(SwingConstants.CENTER);
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
