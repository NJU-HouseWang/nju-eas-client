package NJU.HouseWang.nju_eas_client.ui.MainUI.EditItemUI;

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

public class EditCommonCourseUI {
	private SchoolDeanUILogic logic = new SchoolDeanUILogic();
	private JFrame frame = null;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = 0;
	private int height = 0;
	private String[] item = null;
	private String[] origin = null;
	private String itemInfo = null;
	private JPanel panel = null;
	private JLabel[] iteml = null;
	private JTextField[] itemtf = null;
	private JButton confirmBtn = null;
	private JButton cancelBtn = null;
	private GridLayout gl = null;

	public EditCommonCourseUI(String id) {
		Object o = logic.showCourseEditHead();
		if (o instanceof String[]) {
			item = (String[]) o;
		}
		o = logic.showCourseEdit(id);
		if (o instanceof String[]) {
			origin = (String[]) o;
		}
		frame = new JFrame();
		frame.setTitle("修改项目：通识课 ");
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
			itemtf[i].setText(origin[i]);
			iteml[i].setFont(new Font("微软雅黑", Font.PLAIN, 12));
			itemtf[i].setFont(new Font("微软雅黑", Font.PLAIN, 12));
			panel.add(iteml[i]);
			panel.add(itemtf[i]);
		}
		itemtf[0].setEditable(false);
		itemtf[5].setEditable(false);
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
		itemtf[4].addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (!itemtf[4].getText().equals("")) {
					String name = logic.showTeacherName(itemtf[4].getText());
					itemtf[5].setText(name);
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
					String itemtmp = itemtf[i].getText();
					if (itemtmp == "") {
						JOptionPane.showMessageDialog(frame,
								Feedback.ITEM_EMPTY);
						itemInfo = null;
						break;
					}
					itemInfo += itemtmp + "；";
				}
				if (itemInfo != null) {
					Feedback fb = logic.editCommonCourse(itemInfo);
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
}
