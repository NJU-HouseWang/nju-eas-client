package NJU.HouseWang.nju_eas_client.ui.CommonUI.Bar;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class FunctionBar extends JPanel {
	public FunctionBar() {
		setSize(1000, 45);
		setBackground(Color.getHSBColor(0, 0, (float) 0.88));
		FlowLayout fl = new FlowLayout();
		setLayout(fl);
		fl.setAlignment(FlowLayout.LEFT);
		fl.setHgap(0);
		fl.setVgap(0);
	}
}
