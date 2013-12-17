package NJU.HouseWang.nju_eas_client.ui.MainUI.TeacherUI;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class EditMyCoursePanel extends JPanel {
	JLabel label = new JLabel("课程介绍：");
	JTextArea textArea = new JTextArea();
	JLabel label_1 = new JLabel("推荐书目：");
	JTextArea textArea_1 = new JTextArea();
	JLabel label_2 = new JLabel("课程大纲：");
	JTextArea textArea_2 = new JTextArea();
	JButton button = new JButton("确认修改");
	JButton button_1 = new JButton("取消");

	public EditMyCoursePanel() {
		setLayout(null);

		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setBounds(36, 35, 90, 15);
		add(label);

		textArea.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textArea.setBounds(34, 60, 500, 80);
		add(textArea);

		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_1.setBounds(36, 150, 90, 15);
		add(label_1);

		textArea_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textArea_1.setBounds(34, 175, 500, 80);
		add(textArea_1);

		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_2.setBounds(38, 273, 90, 15);
		add(label_2);

		textArea_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textArea_2.setBounds(36, 298, 500, 80);
		add(textArea_2);

		button.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button.setBounds(149, 402, 93, 23);
		add(button);

		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button_1.setBounds(302, 402, 93, 23);
		add(button_1);
	}
}
