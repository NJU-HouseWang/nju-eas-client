package NJU.HouseWang.nju_eas_client.ui.CommonUI.Panel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SubPanel extends JPanel {
	private JLabel panelName = null;
	private JPanel top = null;
	private JPanel left = null;
	private JPanel right = null;
	private JPanel bottom = null;
	private JPanel center = null;

	public SubPanel(String name, int width, int height) {
		setLayout(null);
		setSize(width, height);

		panelName = new JLabel(" " + name);
		top = new JPanel();
		left = new JPanel();
		right = new JPanel();
		bottom = new JPanel();
		center = new JPanel();

		panelName.setForeground(Color.WHITE);
		panelName.setFont(new Font("微软雅黑", Font.BOLD, 14));
		
		top.setBackground(Color.GRAY);
		left.setBackground(Color.GRAY);
		right.setBackground(Color.GRAY);
		bottom.setBackground(Color.GRAY);
		center.setBackground(Color.WHITE);

		top.setBounds(0, 0, getWidth(), 30);
		left.setBounds(0, 0, 1, getHeight());
		right.setBounds(getWidth() - 1, 0, 1, getHeight());
		bottom.setBounds(0, getHeight() - 1, getWidth(), 1);
		center.setBounds(1, 31, getWidth() - 2, getHeight() - 31);

		((FlowLayout) top.getLayout()).setAlignment(FlowLayout.LEFT);
		top.add(panelName);
		add(top);
		add(left);
		add(right);
		add(bottom);
		add(center);
	}

	public JLabel getPanelName() {
		return panelName;
	}

	public void setPanelName(JLabel panelName) {
		this.panelName = panelName;
	}

	public JPanel getTopPanel() {
		return top;
	}

	public void setTopPanel(JPanel top) {
		this.top = top;
	}

	public JPanel getCenterPanel() {
		return center;
	}

	public void setCenterPanel(JPanel center) {
		this.center = center;
	}
}
