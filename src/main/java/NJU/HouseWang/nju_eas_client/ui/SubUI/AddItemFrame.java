package NJU.HouseWang.nju_eas_client.ui.SubUI;

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

import NJU.HouseWang.nju_eas_client.net.ClientPool;
import NJU.HouseWang.nju_eas_client.netService.NetService;
import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;

public class AddItemFrame extends JFrame {
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

	public AddItemFrame(String itemName, String[] item) {
		this.itemName = itemName;
		setTitle("新增项目：" + itemName);
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
		add(panel);
		setAlwaysOnTop(true);
		addListener();
		pack();
		width = this.getWidth();
		height = this.getHeight();
		setBounds(((int) screenSize.getWidth() - width) / 2,
				((int) screenSize.getHeight() - height) / 2 - 30, width, height);
		setVisible(true);
		setResizable(false);
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
					sendAddCommand();
				}
			}
		});
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
	}

	public void sendAddCommand() {
		String command = "add；" + itemName + "；" + itemInfo;
		try {
			ClientPool cPool = ClientPool.getInstance();
			NetService net = cPool.getClient();
			net.sendCommand(command);
			showFeedBack(net.receiveFeedback());
		} catch (Exception e) {
			showFeedBack(Feedback.INTERNET_ERROR);
			e.printStackTrace();
		}
	}

	public void showFeedBack(String fbStr) {
		Feedback feedback = Feedback.valueOf(fbStr);
		JOptionPane.showMessageDialog(this, feedback.getContent());
	}

	public void showFeedBack(Feedback feedback) {
		JOptionPane.showMessageDialog(this, feedback.getContent());
	}
}
