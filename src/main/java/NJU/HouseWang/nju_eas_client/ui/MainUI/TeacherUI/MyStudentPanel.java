package NJU.HouseWang.nju_eas_client.ui.MainUI.TeacherUI;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Bar.FunctionBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.FunctionBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Panel.SubPanel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CommonTable;

public class MyStudentPanel extends JPanel {
	private FunctionBar fbar = null;
	private FunctionBtn[] fBtn = new FunctionBtn[1];
	private SubPanel edufwp = null;
	private DefaultTableModel dtm = new DefaultTableModel(40, 5);
	private CommonTable infoTable = new CommonTable(dtm);

	public MyStudentPanel() {
		setLayout(null);
		setBackground(Color.white);
		fbar = new FunctionBar();
		fbar.setLocation(0, 0);
		fBtn[0] = new FunctionBtn("ExportBtn");

		for (int i = 0; i < fBtn.length; i++) {
			fbar.add(fBtn[i]);
		}
		add(fbar);

		edufwp = new SubPanel("我的学生列表", 740, 380);
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
