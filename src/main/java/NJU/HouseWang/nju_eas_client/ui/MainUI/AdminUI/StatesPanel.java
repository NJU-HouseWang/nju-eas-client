package NJU.HouseWang.nju_eas_client.ui.MainUI.AdminUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_client.uiLogic.AdminUILogic;

public class StatesPanel extends JPanel {
	private AdminUILogic logic = new AdminUILogic();
	private JTextField textField;
	private JTextField textField_1;
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	private JPanel p4 = new JPanel();
	private JPanel p5 = new JPanel();
	private JLabel currentTermlb = new JLabel("2013-2014学年 第2学期");
	private JButton createNewBtn = new JButton("创建新学期");
	private JButton createBtn = new JButton("创建");
	private JButton cancelBtn = new JButton("取消");
	private JToggleButton chooseBtn = new JToggleButton("关");
	private JToggleButton byElectBtn = new JToggleButton("关");
	private JToggleButton quitBtn = new JToggleButton("关");
	private JLabel generateYear = new JLabel("????");

	public StatesPanel() {
		setLayout(null);

		p1.setBounds(200, 128, 400, 45);
		add(p1);
		p1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel label = new JLabel("当前学期：");
		p1.add(label);

		p1.add(currentTermlb);

		createNewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				p1.setVisible(false);
				p5.setVisible(true);
			}
		});
		p1.add(createNewBtn);

		p2.setBounds(200, 183, 400, 45);
		add(p2);

		JLabel label_2 = new JLabel("通识课选课开关：");
		p2.add(label_2);

		chooseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Feedback fb = logic.swicthStates("selectCommon", new Boolean(
						!chooseBtn.isSelected()).toString());
				if (fb.equals(Feedback.OPERATION_SUCCEED)) {
					fb = logic.processSelection();
				}
				if (!fb.equals(Feedback.OPERATION_SUCCEED)) {
					JOptionPane.showMessageDialog(null, fb.getContent());
				} else {
					chooseBtn.setSelected(!chooseBtn.isSelected());
				}
			}
		});
		p2.add(chooseBtn);

		p3.setBounds(200, 238, 400, 45);
		add(p3);

		JLabel label_3 = new JLabel("通识课补选开关：");
		p3.add(label_3);

		byElectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Feedback fb = logic.swicthStates("byelectCommon", new Boolean(
						!byElectBtn.isSelected()).toString());
				if (!fb.equals(Feedback.OPERATION_SUCCEED)) {
					JOptionPane.showMessageDialog(null, fb.getContent());
				} else {
					byElectBtn.setSelected(!byElectBtn.isSelected());
				}
			}
		});
		p3.add(byElectBtn);

		p4.setBounds(200, 293, 400, 45);
		add(p4);

		JLabel label_4 = new JLabel("通识课退选开关：");
		p4.add(label_4);

		quitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Feedback fb = logic.swicthStates("byelectCommon", new Boolean(
						!quitBtn.isSelected()).toString());
				if (!fb.equals(Feedback.OPERATION_SUCCEED)) {
					JOptionPane.showMessageDialog(null, fb.getContent());
				} else {
					byElectBtn.setSelected(!quitBtn.isSelected());
				}
			}
		});
		p4.add(quitBtn);

		p5.setBounds(125, 128, 550, 45);
		add(p5);
		p5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel label_5 = new JLabel("创建学期：");
		p5.add(label_5);

		textField = new JTextField();
		p5.add(textField);
		textField.setColumns(4);

		JLabel label_6 = new JLabel("-");
		p5.add(label_6);

		p5.add(generateYear);

		JLabel label_8 = new JLabel("学年");
		p5.add(label_8);

		JLabel label_9 = new JLabel("第");
		p5.add(label_9);

		textField_1 = new JTextField();
		p5.add(textField_1);
		textField_1.setColumns(1);

		JLabel label_10 = new JLabel("学期");
		p5.add(label_10);

		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 创建新学期操作
			}
		});
		p5.add(createBtn);

		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1.setVisible(true);
				p5.setVisible(false);
			}
		});
		p5.add(cancelBtn);

		p5.setVisible(false);
	}
}
