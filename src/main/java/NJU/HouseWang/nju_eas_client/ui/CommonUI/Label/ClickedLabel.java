package NJU.HouseWang.nju_eas_client.ui.CommonUI.Label;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class ClickedLabel extends JLabel {
	private String btnName = null;
	public ClickedLabel(String name) {
		this.btnName = name;
		setText("<html>" + btnName + "</html>");
		setFont(new Font("微软雅黑", Font.BOLD, 12));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				((JLabel) e.getSource()).setText("<html><u>" + btnName
						+ "</u></html>");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				((JLabel) e.getSource()).setText("<html>" + btnName + "</html>");
			}
		});
	}

	public void setLabelText(String text) {
		btnName = text;
		setText("<html>" + btnName + "</html>");
	}

}
