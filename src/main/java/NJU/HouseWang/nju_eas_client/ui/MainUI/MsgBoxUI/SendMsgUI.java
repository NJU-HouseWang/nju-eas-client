package NJU.HouseWang.nju_eas_client.ui.MainUI.MsgBoxUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.CommonBtn;
import NJU.HouseWang.nju_eas_client.uiLogic.MsgBoxUILogic;
import NJU.HouseWang.nju_eas_client.vo.Feedback;
import NJU.HouseWang.nju_eas_client.vo.MessageVO;

public class SendMsgUI {
	private MsgBoxUILogic logic = new MsgBoxUILogic();
	private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	private JFrame frame = new JFrame();
	private JLabel receiveId = new JLabel("收件人ID：");
	private JLabel receiveName = new JLabel("收件人：");
	private JLabel topic = new JLabel("主题：");
	private JLabel content = new JLabel("内容：");
	private JTextField receiveIdtf = new JTextField(20);
	private JTextField receiveNametf = new JTextField(20);
	private JTextField topictf = new JTextField(60);
	private JTextArea contenttf = new JTextArea();
	private JPanel panel = new JPanel();
	private JPanel panel1 = new JPanel(null);
	private CommonBtn save_draftBtn = new CommonBtn("保存草稿");
	private CommonBtn delBtn = new CommonBtn("删除草稿");
	private CommonBtn sendBtn = new CommonBtn("发送");
	private CommonBtn cancelBtn = new CommonBtn("取消");

	private MessageVO msg = null;

	public SendMsgUI() {
		frame.setTitle("发送消息");
		frame.setBounds((screen.width - 570) / 2, (screen.height - 480) / 2,
				570, 480);
		frame.setLayout(null);

		receiveId.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		receiveName.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		topic.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		content.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		receiveIdtf.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		receiveNametf.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		receiveNametf.setEditable(false);
		topictf.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		contenttf.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		receiveId.setBounds(30, 20, 100, 30);
		receiveName.setBounds(310, 20, 100, 30);
		topic.setBounds(30, 50, 100, 30);
		content.setBounds(30, 80, 100, 30);
		receiveIdtf.setBounds(130, 23, 150, 24);
		receiveNametf.setBounds(380, 23, 150, 24);
		topictf.setBounds(130, 53, 400, 24);
		contenttf.setBounds(1, 1, 398, 298);
		contenttf.setColumns(50);
		contenttf.setLineWrap(true);
		contenttf.setAutoscrolls(true);
		panel1.setBackground(Color.gray);
		panel1.setBounds(130, 83, 400, 300);
		panel1.add(contenttf);
		panel.setBounds(10, 400, 530, 40);
		((FlowLayout) panel.getLayout()).setHgap(80);
		panel.add(save_draftBtn);
		panel.add(sendBtn);
		panel.add(cancelBtn);

		frame.add(receiveId);
		frame.add(receiveName);
		frame.add(topic);
		frame.add(content);
		frame.add(receiveIdtf);
		frame.add(receiveNametf);
		frame.add(topictf);
		frame.add(panel1);
		frame.add(panel);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		setListener();
	}

	public SendMsgUI(MessageVO msg) {
		this.msg = msg;
		frame.setTitle("发送消息");
		frame.setBounds((screen.width - 570) / 2, (screen.height - 480) / 2,
				570, 480);
		frame.setLayout(null);

		receiveId.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		receiveName.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		topic.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		content.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		receiveIdtf.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		receiveNametf.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		receiveNametf.setEditable(false);
		topictf.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		contenttf.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		receiveId.setBounds(30, 20, 100, 30);
		receiveName.setBounds(310, 20, 100, 30);
		topic.setBounds(30, 50, 100, 30);
		content.setBounds(30, 80, 100, 30);
		receiveIdtf.setBounds(130, 23, 150, 24);
		receiveNametf.setBounds(380, 23, 150, 24);
		topictf.setBounds(130, 53, 400, 24);
		contenttf.setBounds(1, 1, 398, 298);
		contenttf.setColumns(50);
		contenttf.setLineWrap(true);
		contenttf.setAutoscrolls(true);
		panel1.setBackground(Color.gray);
		panel1.setBounds(130, 83, 400, 300);
		panel1.add(contenttf);
		panel.setBounds(10, 400, 530, 40);
		((FlowLayout) panel.getLayout()).setHgap(50);
		panel.add(save_draftBtn);
		panel.add(delBtn);
		panel.add(sendBtn);
		panel.add(cancelBtn);

		frame.add(receiveId);
		frame.add(receiveName);
		frame.add(topic);
		frame.add(content);
		frame.add(receiveIdtf);
		frame.add(receiveNametf);
		frame.add(topictf);
		frame.add(panel1);
		frame.add(panel);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		setListener();

		receiveIdtf.setText(msg.recipientId);
		receiveNametf.setText(logic.showUserName(msg.senderId));
		topictf.setText(msg.title);
		contenttf.append(msg.content);
	}

	private void setListener() {
		receiveIdtf.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				if (receiveIdtf.getText().equals("")) {
					receiveNametf.setText("");
				} else if (receiveIdtf.getText().equals("null")) {
					receiveNametf.setText("");
				} else {
					receiveNametf.setText(logic.showUserName(receiveIdtf
							.getText()));
				}
			}
		});

		save_draftBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessageVO newMsg = new MessageVO();
				newMsg.senderId = "me";
				newMsg.recipientId = receiveIdtf.getText().replaceAll("；", ";");
				if (newMsg.recipientId.equals("")) {
					newMsg.recipientId = "null";
				}
				newMsg.title = topictf.getText().replaceAll("；", ";");
				if (newMsg.title.equals("")) {
					newMsg.title = "null";
				}
				newMsg.content = contenttf.getText().replaceAll("；", ";");
				if (newMsg.content.equals("")) {
					newMsg.content = "null";
				}
				Feedback fb = logic.saveDraft(newMsg);
				JOptionPane.showMessageDialog(null, fb.getContent());
			}
		});

		sendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessageVO newMsg = new MessageVO();
				newMsg.senderId = "me";
				newMsg.recipientId = receiveIdtf.getText().replaceAll("；", ";");
				newMsg.title = topictf.getText().replaceAll("；", ";");
				newMsg.content = contenttf.getText().replaceAll("；", ";");
				if (newMsg.recipientId.equals("")
						|| newMsg.recipientId.equals("")
						|| newMsg.title.equals("") || newMsg.content.equals("")) {
					JOptionPane.showMessageDialog(null, "消息信息不完整");
					return;
				}

				if (receiveNametf.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "接收方不存在");
					return;
				}

				Feedback fb = null;

				if (msg.id != null) {
					fb = logic.delMessage(2, msg.id);
				}
				if (fb == Feedback.OPERATION_SUCCEED) {
					fb = logic.sendMsg(newMsg);
					if (fb == Feedback.OPERATION_SUCCEED) {
						JOptionPane.showMessageDialog(null, "发送成功！");
						frame.setVisible(false);
						frame.dispose();
					}
				} else {
					JOptionPane.showMessageDialog(null, fb.getContent());
				}
			}
		});

		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Feedback fb = logic.delMessage(2, msg.id);
				if (fb == Feedback.OPERATION_SUCCEED) {
					JOptionPane.showMessageDialog(null, "删除成功！");
					frame.setVisible(false);
					frame.dispose();
				}
			}
		});
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
	}
}
