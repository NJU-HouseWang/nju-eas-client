package NJU.HouseWang.nju_eas_client.ui.CommonUI.Bar;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class BigMenuBar extends JPanel {

	public BigMenuBar() {
		setSize(950, 500);
		setBackground(Color.getHSBColor((float) 0.617, (float) 0.42,
				(float) 0.92));
		FlowLayout fl = new FlowLayout();
		setLayout(fl);
		fl.setAlignment(FlowLayout.CENTER);
		fl.setHgap(50);
		fl.setVgap(80);
		updateUI();
	}
}
