package NJU.HouseWang.nju_eas_client.ui.CommonUI.ComboBox;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;

import NJU.HouseWang.nju_eas_client.uiLogic.CommonUILogic.CommonUILogic;

public class GradeBox extends JComboBox<String> {
	private CommonUILogic logic = new CommonUILogic();

	public GradeBox() {
		setPreferredSize(new Dimension(120, 20));
		setFont(new Font("微软雅黑", Font.BOLD, 14));
		init();
	}

	protected void init() {
		ArrayList<String> list = logic.getGradeList();
		addItem("请选择年级...");
		for (String s : list) {
			addItem(s);
		}
	}
}
