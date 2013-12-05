package NJU.HouseWang.nju_eas_client.ui.MainUI.MsgBoxUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class ReadMsgUI {
	private MsgBoxUILogic logic = new MsgBoxUILogic();
	private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	private JFrame frame = new JFrame();
	private JLabel receiveId = new JLabel("发件人ID：");
	private JLabel receiveName = new JLabel("发件人：");
	private JLabel topic = new JLabel("主题：");
	private JLabel content = new JLabel("内容：");
	private JTextField senderIdtf = new JTextField(20);
	private JTextField senderNametf = new JTextField(20);
	private JTextField topictf = new JTextField(60);
	private JTextArea contenttf = new JTextArea();
	private JPanel panel = new JPanel();
	private JPanel panel1 = new JPanel(null);
	private CommonBtn deleteBtn = new CommonBtn("删除");
	private CommonBtn cancelBtn = new CommonBtn("返回");

	private int type = -1;
	private MessageVO msg = null;

	public ReadMsgUI(int type, MessageVO msg) {
		this.type = type;
		this.msg = msg;
		frame.setTitle("消息详情");
		frame.setBounds((screen.width - 600) / 2, (screen.height - 480) / 2,
				570, 480);
		frame.setLayout(null);

		receiveId.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		receiveName.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		topic.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		content.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		senderIdtf.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		senderIdtf.setEditable(false);
		senderNametf.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		senderNametf.setEditable(false);
		topictf.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		topictf.setEditable(false);
		contenttf.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		contenttf.setEditable(false);

		receiveId.setBounds(30, 20, 100, 30);
		receiveName.setBounds(310, 20, 100, 30);
		topic.setBounds(30, 50, 100, 30);
		content.setBounds(30, 80, 100, 30);
		senderIdtf.setBounds(130, 23, 150, 24);
		senderNametf.setBounds(380, 23, 150, 24);
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
		panel.add(deleteBtn);
		panel.add(cancelBtn);

		frame.add(receiveId);
		frame.add(receiveName);
		frame.add(topic);
		frame.add(content);
		frame.add(senderIdtf);
		frame.add(senderNametf);
		frame.add(topictf);
		frame.add(panel1);
		frame.add(panel);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		senderIdtf.setText(msg.senderId);
		senderNametf.setText(logic.showUserName(msg.senderId));
		topictf.setText(msg.title);
		contenttf.append(msg.content);
		frame.setVisible(true);
		setListener();
	}

	private void setListener() {
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Feedback fb = logic.delMessage(type, msg.id);
				if (fb == Feedback.OPERATION_SUCCEED) {
					JOptionPane.showMessageDialog(null, "删除成功！");
					frame.setVisible(false);
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, fb.getContent());
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
