package NJU.HouseWang.nju_eas_client.ui.MainUI.SchoolDeanUI;

import java.util.HashMap;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CMap;

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
		rowMap.put(0, si);
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
			if (row == -1) {
				return -1;
			}
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
