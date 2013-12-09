package NJU.HouseWang.nju_eas_client.ui.MainUI.StudentUI;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.SubPanel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CommonTable;

public class ChooseCommonPanel extends JPanel {
	private SubPanel choosep = new SubPanel("已选课程列表", 500, 135);
	private SubPanel coup = new SubPanel("通识课列表", 500, 280);
	private SubPanel infop = new SubPanel("课程详细信息", 230, 425);

	private DefaultTableModel dtm1 = new DefaultTableModel(4, 5);
	private CommonTable infoTable1 = new CommonTable(dtm1);

	private DefaultTableModel dtm2 = new DefaultTableModel(10, 5);
	private CommonTable infoTable2 = new CommonTable(dtm2);

	public ChooseCommonPanel() {
		setLayout(null);
		setBackground(Color.white);
		
		choosep.setLocation(30, 25);
		coup.setLocation(30, 170);
		infop.setLocation(540, 25);

		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		infoTable1.setDefaultRenderer(Object.class, r);
		infoTable2.setDefaultRenderer(Object.class, r);

		coup.getCenterPanel().setLayout(new BorderLayout());
		coup.getCenterPanel().add(new JScrollPane(infoTable2),
				BorderLayout.CENTER);
		choosep.getCenterPanel().setLayout(new BorderLayout());
		choosep.getCenterPanel().add(new JScrollPane(infoTable1),
				BorderLayout.CENTER);
		add(choosep);
		add(coup);
		add(infop);
	}
}
