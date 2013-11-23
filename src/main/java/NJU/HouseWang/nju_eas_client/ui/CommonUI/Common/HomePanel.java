package NJU.HouseWang.nju_eas_client.ui.CommonUI.Common;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class HomePanel extends JPanel {

	private ImageIcon imageIcon = new ImageIcon("img/Main/Home.png");

	public HomePanel() {
		super();
		setSize(800, 480);
		setLayout(null);
		updateUI();
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
