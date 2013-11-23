package NJU.HouseWang.nju_eas_client.ui.CommonUI.MenuBtn;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

public class MsgMenuBtn extends JToggleButton {
	private String path = "img/SMenuIcon/";
	private String name = "MsgMenuBtn";
	private String suffix = ".png";
	private JLabel text = new JLabel();

	public MsgMenuBtn(String t) {
		super();
		text.setText(t);
		setIcon(new ImageIcon(path + name + "_0" + suffix));
		setRolloverIcon(new ImageIcon(path + name + "_1" + suffix));
		setSelectedIcon(new ImageIcon(path + name + "_1" + suffix));
		setSize(200, 50);
		setPreferredSize(new Dimension(200, 45));
		FlowLayout fl = new FlowLayout();
		fl.setVgap(8);
		fl.setAlignment(FlowLayout.CENTER);
		setLayout(fl);
		text.setFont(new Font("΢���ź�", Font.BOLD, 15));
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
