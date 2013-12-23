package NJU.HouseWang.nju_eas_client.ui.CommonUI.Bar;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class MenuBar extends JPanel {
	private ButtonGroup group = new ButtonGroup();

	public MenuBar() {
		setSize(1000, 40);
		FlowLayout fl = new FlowLayout();
		setLayout(fl);
		fl.setAlignment(FlowLayout.LEFT);
		fl.setHgap(0);
		fl.setVgap(0);
		setBackground(Color.getHSBColor((float) 0.617, (float) 1, (float) 0.24));
		updateUI();
	}

	@Override
	public Component add(Component comp) {
		Component tmp = super.add(comp);
		group.add((JToggleButton) comp);
		return tmp;
	}
}
