package NJU.HouseWang.nju_eas_client.ui.MainUI.EditItemUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import NJU.HouseWang.nju_eas_client.uiLogic.AdminUILogic;
import NJU.HouseWang.nju_eas_client.vo.Feedback;

public class EditUserUI {
	private AdminUILogic logic = new AdminUILogic();
	private JFrame frame = null;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = 0;
	private int height = 0;
	private String itemName = "";
	private String itemInfo = "";
	private JPanel panel = null;
	private JLabel[] iteml = null;
	private JTextField[] itemtf = null;
	private JButton confirmBtn = null;
	private JButton cancelBtn = null;
	private GridLayout gl = null;

	public EditUserUI(String itemN, String[] origin) {
		this.itemName = itemN.split("；")[0];
		String[] item = (String[]) logic.showInfoTableHead(itemName + "_list");
		frame = new JFrame();
		frame.setTitle("修改项目： " + itemName);
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
		if (itemName.equals("teacher")) {
			switch (origin[1]) {
			case "学校教务老师":
				itemtf[1].setText("SchoolDean");
				break;
			case "院系教务老师":
				itemtf[1].setText("DeptAD");
				break;
			case "任课老师":
				itemtf[1].setText("Teacher");
				break;
			}
			itemtf[1].setEditable(false);
		}
		itemtf[0].setEditable(false);
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
					Feedback fb = logic.editUser(itemName, itemInfo);
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
