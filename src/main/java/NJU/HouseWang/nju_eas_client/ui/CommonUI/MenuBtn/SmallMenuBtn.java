package NJU.HouseWang.nju_eas_client.ui.CommonUI.MenuBtn;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

public class SmallMenuBtn extends JToggleButton {
	private String path = "img/SMenuIcon/";
	private String name = "SMenuBtn";
	private String suffix = ".png";
	private JLabel text = new JLabel();

	public SmallMenuBtn(String t) {
		super();
		text.setText(t);
		setIcon(new ImageIcon(ClassLoader.getSystemResource(path + name + "_0" + suffix)));
		setRolloverIcon(new ImageIcon(ClassLoader.getSystemResource(path + name + "_1" + suffix)));
		setSelectedIcon(new ImageIcon(ClassLoader.getSystemResource(path + name + "_1" + suffix)));
		setSize(90, 45);
		setPreferredSize(new Dimension(90, 45));
		FlowLayout fl = new FlowLayout();
		fl.setVgap(9);
		setLayout(fl);
		text.setFont(new Font("微软雅黑", Font.BOLD, 12));
		text.setForeground(Color.white);
		add(text);
		setBorderPainted(false);
		updateUI();
	}

	@Override
	public void setText(String t) {
		text.setText(t);
	}

	public String getFuncName() {
		return text.getText();
	}
}
