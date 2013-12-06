package NJU.HouseWang.nju_eas_client.ui.CommonUI.Table;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class CommonTable extends JTable {
	public CommonTable() {
		super();
	}

	public CommonTable(DefaultTableModel dtm) {
		super(dtm);
		setFont(new Font("微软雅黑", Font.PLAIN, 12));
		getTableHeader().setFont(new Font("微软雅黑", Font.BOLD, 12));
		setBackground(Color.white);
		setRowHeight(20);
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		setDefaultRenderer(Object.class, r);
	}
}
