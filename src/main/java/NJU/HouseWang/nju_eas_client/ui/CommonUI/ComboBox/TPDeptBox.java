package NJU.HouseWang.nju_eas_client.ui.CommonUI.ComboBox;

import java.util.ArrayList;

import javax.swing.JComboBox;

import NJU.HouseWang.nju_eas_client.uiLogic.CommonUILogic;
import NJU.HouseWang.nju_eas_client.vo.TPDeptVO;

public class TPDeptBox extends JComboBox<TPDeptVO> {
	private CommonUILogic logic = new CommonUILogic();

	public TPDeptBox() {
		init();
	}

	protected void init() {
		ArrayList<TPDeptVO> list = logic.getTPDeptList();
		addItem(new TPDeptVO(null, "请选择院系..."));
		for (TPDeptVO tp : list) {
			addItem(tp);
		}
	}
}
