package NJU.HouseWang.nju_eas_client.ui.CommonUI.Panel;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CommonPanel extends JPanel {
	private String path = "img/Main/";
	private String name = null;
	private String suffix = ".png";

	private ImageIcon imageIcon = new ImageIcon(path + name + suffix);

	public CommonPanel(String name) {
		super();
		this.name = name;
		imageIcon = new ImageIcon(path + name + suffix);
		setLayout(null);
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
