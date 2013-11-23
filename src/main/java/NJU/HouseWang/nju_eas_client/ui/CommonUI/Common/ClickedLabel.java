package NJU.HouseWang.nju_eas_client.ui.CommonUI.Common;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class ClickedLabel extends JLabel {
	private String name = null;

	public ClickedLabel(final String name) {
		setText("<html>" + name + "</html>");
		setFont(new Font("微软雅黑", Font.BOLD, 12));
		setForeground(Color.WHITE);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				((JLabel) e.getSource()).setText("<html><u>" + name
						+ "</u></html>");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				((JLabel) e.getSource()).setText("<html>" + name + "</html>");
				((JLabel) e.getSource()).setForeground(Color.WHITE);
			}
		});
	}
}
