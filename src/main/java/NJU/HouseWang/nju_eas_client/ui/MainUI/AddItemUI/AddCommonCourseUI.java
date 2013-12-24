package NJU.HouseWang.nju_eas_client.ui.MainUI.AddItemUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import NJU.HouseWang.nju_eas_client.uiLogic.SchoolDeanUILogic;
import NJU.HouseWang.nju_eas_client.vo.Feedback;

public class AddCommonCourseUI {
	private SchoolDeanUILogic logic = new SchoolDeanUILogic();
	private JFrame frame = null;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = 0;
	private int height = 0;
	private String itemName = "";
	private String[] item = null;
	private String itemInfo = "";
	private JPanel panel = null;
	private JLabel[] iteml = null;
	private JTextField[] itemtf = null;
	private JButton confirmBtn = null;
	private JButton cancelBtn = null;
	private GridLayout gl = null;

	public AddCommonCourseUI(String itemName) {
		this.itemName = itemName;
		Object o = logic.showCourseEditHead();
		if (o instanceof String[]) {
			item = (String[]) o;
		}
		frame = new JFrame();
		frame.setTitle("新增项目：通识课");
		panel = new JPanel();
		iteml = new JLabel[item.length];
		itemtf = new JTextField[item.length];
		confirmBtn = new JButton("确认");
		cancelBtn = new JButton("取消");
		gl = new GridLayout(item.length + 1, 2);
		gl.setVgap(10);
		gl.setHgap(10);
		panel.setLayout(gl);
		for (int i = 0; i < item.length; i++) {
			iteml[i] = new JLabel(item[i] + ":");
			iteml[i].setHorizontalAlignment(JLabel.CENTER);
			itemtf[i] = new JTextField(15);
			iteml[i].setFont(new Font("微软雅黑", Font.PLAIN, 12));
			itemtf[i].setFont(new Font("微软雅黑", Font.PLAIN, 12));
			panel.add(iteml[i]);
			panel.add(itemtf[i]);
		}
		itemtf[4].setEditable(false);
		panel.add(confirmBtn);
		panel.add(cancelBtn);
		frame.add(panel);
		frame.setAlwaysOnTop(true);
		addListener();
		frame.pack();
		width = frame.getWidth();
		height = frame.getHeight();
		frame.setBounds(((int) screenSize.getWidth() - width) / 2,
				((int) screenSize.getHeight() - height) / 2 - 30, width, height);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public void addListener() {
		itemtf[3].addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (!itemtf[3].getText().equals("")) {
					String name = logic.showTeacherName(itemtf[3].getText());
					itemtf[4].setText(name);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {

			}
		});
		confirmBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < itemtf.length; i++) {
					String item = itemtf[i].getText();
					if (item.equals("") || item.equals("null")) {
						showFeedBack(Feedback.ITEM_EMPTY);
						itemInfo = null;
						break;
					}
					itemInfo += item + "；";
				}
				if (itemInfo != null) {
					Feedback fb = logic.addCommonCourse(itemInfo);
					JOptionPane.showMessageDialog(frame, fb.getContent());
					frame.setVisible(false);
					frame.dispose();
				}
			}
		});
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
	}

	public void showFeedBack(String fbStr) {
		Feedback feedback = Feedback.valueOf(fbStr);
		JOptionPane.showMessageDialog(frame, feedback.getContent());
	}

	public void showFeedBack(Feedback feedback) {
		JOptionPane.showMessageDialog(frame, feedback.getContent());
	}
}
