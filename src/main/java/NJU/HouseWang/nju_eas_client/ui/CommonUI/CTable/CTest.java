package NJU.HouseWang.nju_eas_client.ui.CommonUI.CTable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class CTest {
	public static void main(String args[]) {
		JFrame jf = new JFrame("Table with cell spanning");

		CMap m = new CMap1();
		DefaultTableModel tm = new DefaultTableModel(16, 10) {
			public boolean isCellEditable(int indexRow, int indexColumn) {
				return false;
			}
		};
		tm.setValueAt("port1", 3, 0);
		CTable ct = new CTable(m, tm);
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		ct.setDefaultRenderer(Object.class, r);
		jf.getContentPane().add(new JScrollPane(ct));
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(500, 500);
		jf.setVisible(true);
	}
}