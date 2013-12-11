package NJU.HouseWang.nju_eas_client.ui.MainUI.AdminUI;

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

import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_client.uiLogic.AdminUILogic;

public class AddUserUI {
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

	public AddUserUI(String itemName, String[] item) {
		this.itemName = itemName;
		frame = new JFrame();
		frame.setTitle("新增项目：" + itemName);
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
					String item = itemtf[i].getText();
					if (item == "") {
						showFeedBack(Feedback.ITEM_EMPTY);
						itemInfo = null;
						break;
					}
					itemInfo += item + "；";
				}
				if (itemInfo != null) {
					Feedback fb = logic.addUser(itemName, itemInfo);
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
