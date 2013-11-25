package NJU.HouseWang.nju_eas_client.ui.CommonUI.Common;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddItemFrame extends JFrame {
	private JPanel panel = null;
	private JLabel[] iteml = null;
	private JTextField[] itemtf = null;
	private JButton confirmBtn = null;
	private JButton cancelBtn = null;

	public AddItemFrame(String[] item) {
		panel = new JPanel();
		iteml = new JLabel[item.length];
		itemtf = new JTextField[item.length];
		confirmBtn = new JButton("确认");
		cancelBtn = new JButton("取消");
		GridLayout gl = new GridLayout(item.length+1, 2);
		gl.setVgap(10);
		gl.setHgap(10);
		panel.setLayout(gl);
		for (int i = 0; i < item.length; i++) {
			iteml[i] = new JLabel(item[i]+":");
			iteml[i].setHorizontalAlignment(JLabel.CENTER);
			itemtf[i] = new JTextField(15);
			panel.add(iteml[i]);
			panel.add(itemtf[i]);
		}
		panel.add(confirmBtn);
		panel.add(cancelBtn);
		add(panel);
		pack();
		setVisible(true);
	}
	
	public String[] getItemStr() {
		return null;
	}
}
