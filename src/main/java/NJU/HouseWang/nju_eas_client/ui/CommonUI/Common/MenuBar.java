package NJU.HouseWang.nju_eas_client.ui.CommonUI.Common;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class MenuBar extends JPanel {
	private ImageIcon imageIcon = new ImageIcon("img/MenuBar.png");
	private ButtonGroup group = new ButtonGroup();

	public MenuBar() {
		setSize(800, 50);
		FlowLayout fl = new FlowLayout();
		setLayout(fl);
		fl.setAlignment(FlowLayout.LEFT);
		fl.setHgap(0);
		fl.setVgap(0);
		updateUI();
	}

	@Override
	public Component add(Component comp) {
		Component tmp = super.add(comp);
		group.add((JToggleButton) comp);
		return tmp;
	}

	@Override
	protected void paintChildren(Graphics g) {
		super.paintChildren(g);
		for (int i = 0; i < this.getComponents().length; i++) {
			this.getComponent(i).repaint();
		}
		g.dispose();
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		super.paintComponent(g2D);
		if (imageIcon != null) {
			g2D.drawImage(imageIcon.getImage(), 0, 0, this.getWidth(),
					this.getHeight(), this);
		}
		g2D.dispose();
	}
}
