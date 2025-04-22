package com.yuanhao.server.ui;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class DisplayMsg {
	public static JTextPane actionMsgTP;//���
	public static JTextPane callMsgTP;//�ұ�
	

	public static void displayActionMessage(String msg){
		if (actionMsgTP != null) {
			Document doc = actionMsgTP.getDocument();
			try {
				doc.insertString(doc.getLength(), msg+"\n", null);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
	}
		public static void displayCallMessage(String msg){
			if (callMsgTP != null) {
				Document doc = callMsgTP.getDocument();
				try {
					doc.insertString(doc.getLength(), msg+"\n", null);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		}
}
