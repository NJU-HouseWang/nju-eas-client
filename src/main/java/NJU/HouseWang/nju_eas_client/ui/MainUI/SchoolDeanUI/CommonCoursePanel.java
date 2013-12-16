package NJU.HouseWang.nju_eas_client.ui.MainUI.SchoolDeanUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Bar.FunctionBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.FunctionBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Panel.SubPanel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CommonTable;

public class CommonCoursePanel extends JPanel {
	private FunctionBar fbar = new FunctionBar();
	private FunctionBtn[] fBtn = new FunctionBtn[5];
	private SubPanel cp = new SubPanel("通识课列表", 740, 380);
	private DefaultTableModel dtm = new DefaultTableModel(0, 5);
	private CommonTable infoTable = new CommonTable(dtm);
	private JButton refreshBtn = new JButton();
	
	public CommonCoursePanel() {
		setLayout(null);
		setBackground(Color.white);
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

		cp.setLocation(30, 70);

		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		infoTable.setDefaultRenderer(Object.class, r);

		cp.getCenterPanel().setLayout(new BorderLayout());
		cp.getCenterPanel().add(new JScrollPane(infoTable),
				BorderLayout.CENTER);
		refreshBtn.setPreferredSize(new Dimension(25,25));
		cp.getTopPanel().add(refreshBtn);
		add(cp);
		setListener();
	}
	
	private void setListener() {
		
	}
}