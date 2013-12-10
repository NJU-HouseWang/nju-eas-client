package NJU.HouseWang.nju_eas_client.ui.MainUI.MsgBoxUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CommonTable;

public class MsgOutPanel extends JPanel {
	private DefaultTableModel tm = new DefaultTableModel(5, 3){
		public boolean isCellEditable(int arg0, int arg1) {
			return false;
		}
	};
	private CommonTable table = new CommonTable(tm);
	private JScrollPane js = new JScrollPane(table);

	public MsgOutPanel() {
		setLayout(new BorderLayout());
		setBackground(Color.black);
		table.setShowVerticalLines(false);
		table.setSelectionMode(0);
		tm.setColumnIdentifiers(new String[]{"发件人","主题","发送时间"});
		js.setPreferredSize(new Dimension(548,423));
		add(js,BorderLayout.CENTER);
	}
}
