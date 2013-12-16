package NJU.HouseWang.nju_eas_client.ui.CommonUI.ComboBox;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;

import NJU.HouseWang.nju_eas_client.uiLogic.CommonUILogic.CommonUILogic;
import NJU.HouseWang.nju_eas_client.vo.DeptVO;

public class DeptBox extends JComboBox<DeptVO> {
	private CommonUILogic logic = new CommonUILogic();

	public DeptBox() {
		setPreferredSize(new Dimension(120, 20));
		setFont(new Font("微软雅黑", Font.BOLD, 14));
		init();
	}

	protected void init() {
		ArrayList<DeptVO> list = logic.getDeptList();
		addItem(new DeptVO(null, "请选择院系..."));
		for (DeptVO s : list) {
			addItem(s);
		}
	}
}
