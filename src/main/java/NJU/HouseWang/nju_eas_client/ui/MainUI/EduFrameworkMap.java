package NJU.HouseWang.nju_eas_client.ui.MainUI;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.CTable.CMap;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.CTable.CTable;

public class EduFrameworkMap implements CMap {
	private String[][] originTable = null;
	private HashMap<Integer, HashMap<Integer, SpanInfo>> columnMap = new HashMap<Integer, HashMap<Integer, SpanInfo>>();

	public EduFrameworkMap(String[][] originTable) {
		this.originTable = originTable;
		scanColumn(0);
		scanColumn(2);
	}

	public void scanColumn(int columnPoint) {
		HashMap<Integer, SpanInfo> rowMap = new HashMap<Integer, SpanInfo>();
		SpanInfo si = new SpanInfo();
		si.name = originTable[0][columnPoint];
		si.row = 0;
		si.num = 0;
		for (int i = 0; i < originTable.length; i++) {
			if (si.name.equals(originTable[i][columnPoint])) {
				si.num++;
				if (i == originTable.length - 1) {
					for (int j = si.row; j < si.row + si.num; j++) {
						rowMap.put(j, si);
					}
				}
			} else {
				for (int j = si.row; j < si.row + si.num; j++) {
					rowMap.put(j, si);
				}
				si = new SpanInfo();
				si.name = originTable[i][columnPoint];
				si.row = i;
				si.num = 1;
				if (i == originTable.length - 1) {
					for (int j = si.row; j < si.row + si.num; j++) {
						rowMap.put(j, si);
					}
				}
			}
		}
		columnMap.put(columnPoint, rowMap);
	}

	@Override
	public int span(int row, int column) {
		switch (column) {
		case 0:
			SpanInfo si1 = columnMap.get(0).get(row);
			return si1.num;
		case 1:
		case 2:
		case 3:
		case 6:
			SpanInfo si2 = columnMap.get(2).get(row);
			return si2.num;
		default:
			return 1;
		}
	}

	@Override
	public int visibleCell(int row, int column) {
		switch (column) {
		case 0:
			SpanInfo si1 = columnMap.get(0).get(row);
			return si1.row;
		case 1:
		case 2:
		case 3:
		case 6:
			SpanInfo si2 = columnMap.get(2).get(row);
			return si2.row;
		default:
			return row;
		}
	}
//
//	public static void main(String args[]) {
//		JFrame jf = new JFrame("Table with cell spanning");
//		String[][] list = new String[][] {
//				{ "A", "V", "V", "V", "1", "1", "V" },
//				{ "A", "C", "C", "C", "2", "2", "C" },
//				{ "A", "C", "C", "C", "3", "3", "C" },
//				{ "B", "V", "V", "V", "4", "4", "V" },
//				{ "B", "F", "F", "F", "5", "5", "F" },
//
//		};
//		EduFrameworkMap m = new EduFrameworkMap(list);
//		DefaultTableModel tm = new DefaultTableModel(5, 7) {
//			public boolean isCellEditable(int indexRow, int indexColumn) {
//				return false;
//			}
//		};
//		for (int i = 0; i < list.length; i++) {
//			for (int j = 0; j < list[i].length; j++) {
//				tm.setValueAt(list[i][j], i, j);
//			}
//		}
//		tm.setColumnIdentifiers(new String[] { "as", "sdf", "sf", "sdf",
//				"asdf", "adsf", "a" });
//		// tm.setValueAt("port1", 0, 0);
//		// tm.setValueAt("port1", 1, 0);
//
//		CTable ct = new CTable(m, tm);
//		ct.setEnabled(false);
//		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
//		r.setHorizontalAlignment(JLabel.CENTER);
//		ct.setDefaultRenderer(Object.class, r);
//		jf.getContentPane().add(new JScrollPane(ct));
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		jf.setSize(500, 500);
//		jf.setVisible(true);
//	}
}

class SpanInfo {
	String name = null;
	int row = 0;
	int num = 0;

	@Override
	public String toString() {
		return "SpanInfo [name=" + name + ", row=" + row + ", num=" + num + "]";
	}
}
