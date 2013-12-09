package NJU.HouseWang.nju_eas_client.ui.MainUI.StudentUI;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.FunctionBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.SubPanel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.FunctionBtn.FunctionBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CommonTable;

public class MyCoursePanel extends JPanel {
	private FunctionBar fbar = null;
	private FunctionBtn[] fBtn = new FunctionBtn[5];
	private SubPanel edufwp = null;
	private DefaultTableModel dtm = new DefaultTableModel(40, 5);
	private CommonTable infoTable = new CommonTable(dtm);

	public MyCoursePanel() {
		setLayout(null);
		setBackground(Color.white);
		fbar = new FunctionBar();
		fbar.setLocation(0, 0);
		fBtn[0] = new FunctionBtn("AddBtn");
		fBtn[1] = new FunctionBtn("ModifyBtn");
		fBtn[2] = new FunctionBtn("DelBtn");
		fBtn[3] = new FunctionBtn("ImportBtn");
		fBtn[4] = new FunctionBtn("ExportBtn");

		for (int i = 0; i < fBtn.length; i++) {
			fbar.add(fBtn[i]);
		}
		add(fbar);

		edufwp = new SubPanel("我的课程列表", 740, 380);
		edufwp.setLocation(30, 70);

		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		infoTable.setDefaultRenderer(Object.class, r);

		edufwp.getCenterPanel().setLayout(new BorderLayout());
		edufwp.getCenterPanel().add(new JScrollPane(infoTable),
				BorderLayout.CENTER);
		add(edufwp);

	}
}
