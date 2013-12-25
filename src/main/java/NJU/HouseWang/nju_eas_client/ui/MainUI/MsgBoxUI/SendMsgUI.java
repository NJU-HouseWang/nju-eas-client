package NJU.HouseWang.nju_eas_client.ui.MainUI.MsgBoxUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.CommonBtn;

public class SendMsgUI {
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
	private CommonBtn save_draftBtn = new CommonBtn("存草稿");
	private CommonBtn sendBtn = new CommonBtn("发送");
	private CommonBtn cancelBtn = new CommonBtn("取消");

	public SendMsgUI() {
		frame.setTitle("发送消息");
		frame.setBounds((screen.width - 600) / 2, (screen.height - 480) / 2,
				600, 480);
		frame.setLayout(null);

		receiveId.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		receiveName.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		topic.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		content.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		receiveIdtf.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		receiveNametf.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		topictf.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		contenttf.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		receiveId.setBounds(50, 20, 100, 30);
		receiveName.setBounds(310, 20, 100, 30);
		topic.setBounds(50, 50, 100, 30);
		content.setBounds(50, 80, 100, 30);
		receiveIdtf.setBounds(130, 23, 150, 24);
		receiveNametf.setBounds(380, 23, 150, 24);
		topictf.setBounds(130, 53, 400, 24);
		contenttf.setBounds(130, 83, 400, 300);
		contenttf.setColumns(50);
		contenttf.setLineWrap(true);
		contenttf.setAutoscrolls(true);
		panel.setBounds(30, 400, 530, 40);
		((FlowLayout) panel.getLayout()).setHgap(50);
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
		frame.add(contenttf);
		frame.add(panel);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new SendMsgUI();
	}
}
