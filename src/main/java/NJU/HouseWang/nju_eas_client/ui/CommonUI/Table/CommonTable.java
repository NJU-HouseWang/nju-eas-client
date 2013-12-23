package NJU.HouseWang.nju_eas_client.ui.CommonUI.Table;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

public class CommonTable extends JTable {

	public CommonTable() {
		super();
		setFont(new Font("微软雅黑", Font.PLAIN, 12));
		getTableHeader().setFont(new Font("微软雅黑", Font.BOLD, 12));
		setBackground(Color.white);
		setRowHeight(20);
		DefaultTableCellRenderer cell = new DefaultTableCellRenderer();
		cell.setHorizontalAlignment(JLabel.CENTER);
		setDefaultRenderer(Object.class, cell);
		DefaultTableCellRenderer head = new DefaultTableCellRenderer();
		head.setBackground(Color.DARK_GRAY);
		head.setForeground(Color.white);
		head.setHorizontalAlignment(JLabel.CENTER);
		getTableHeader().setDefaultRenderer(head);
		this.updateUI();
	}

	public CommonTable(TableModel dtm) {
		setModel(dtm);
		setFont(new Font("微软雅黑", Font.PLAIN, 12));
		getTableHeader().setFont(new Font("微软雅黑", Font.BOLD, 12));
		setBackground(Color.white);
		setRowHeight(20);
		DefaultTableCellRenderer cell = new DefaultTableCellRenderer();
		cell.setHorizontalAlignment(JLabel.CENTER);
		setDefaultRenderer(Object.class, cell);
		DefaultTableCellRenderer head = new DefaultTableCellRenderer();
		head.setBackground(Color.DARK_GRAY);
		head.setForeground(Color.white);
		head.setHorizontalAlignment(JLabel.CENTER);
		getTableHeader().setDefaultRenderer(head);
	}
}
