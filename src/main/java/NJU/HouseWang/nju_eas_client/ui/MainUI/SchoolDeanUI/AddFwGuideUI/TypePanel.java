package NJU.HouseWang.nju_eas_client.ui.MainUI.SchoolDeanUI.AddFwGuideUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CommonTable;

public class TypePanel extends JPanel {
	private int moduleNum = 0;
	private ArrayList<String> moduleInfo = null;
	private JLabel[] moduleLb;

	private JPanel p2main = new JPanel();
	private JScrollPane p2sp = new JScrollPane(p2main);

	private DefaultTableModel[] typeDtm = null;
	private CommonTable[] typeTable = null;
	private JScrollPane[] tableScroll = null;

	private ArrayList<String> typeInfo = new ArrayList<String>();

	public TypePanel(ArrayList<String> moduleInfo) {
		this.moduleNum = moduleInfo.size();
		this.moduleInfo = moduleInfo;
		moduleLb = new JLabel[moduleNum];
		typeDtm = new DefaultTableModel[moduleNum];
		typeTable = new CommonTable[moduleNum];
		tableScroll = new JScrollPane[moduleNum];
		setLayout(new BorderLayout());
		p2sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		p2sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		p2main.setPreferredSize(new Dimension(595, moduleNum * 200));
		p2main.setLayout(new BoxLayout(p2main, BoxLayout.Y_AXIS));
		for (int i = 0; i < moduleNum; i++) {
			typeDtm[i] = new DefaultTableModel(15, 7);
			typeDtm[i].setColumnIdentifiers(new String[] { "课程性质", "序列",
					"课程类型", "最低学分", "最高学分", "起始学期", "终止学期" });
			typeDtm[i]
					.insertRow(0, new String[] { "", "", "", "", "", "", "" });
			moduleLb[i] = new JLabel("模块" + moduleInfo.get(i), JLabel.LEFT);
			moduleLb[i].setFont(new Font("微软雅黑", Font.PLAIN, 20));
			moduleLb[i].setPreferredSize(new Dimension(595, 50));
			p2main.add(moduleLb[i]);
			typeTable[i] = new CommonTable(typeDtm[i]);
			typeTable[i].setPreferredScrollableViewportSize(new Dimension(400,
					150));
			tableScroll[i] = new JScrollPane(typeTable[i]);
			p2main.add(tableScroll[i]);
		}

		add(p2sp, BorderLayout.CENTER);
	}

	public boolean collectTypeInfo() {
		typeInfo.clear();
		for (int i = 0; i < moduleNum; i++) {
			int rowPoint = 0;
			while (true) {
				String line = "";
				for (int j = 0; j < 7; j++) {
					String cell = null;
					if (typeTable[i].getValueAt(rowPoint, j) != null) {
						cell = (String) typeTable[i].getValueAt(rowPoint, j);
						line += cell + "；";
					} else {
						line = null;
						break;
					}
				}
				if (line != null) {
					System.out.println("Get Type Info:"
							+ new String(moduleInfo.get(i)) + line);
					typeInfo.add(new String(moduleInfo.get(i)) + line);
				} else {
					break;
				}
				rowPoint++;
			}
		}
		return true;
	}

	public ArrayList<String> getTypeInfoList() {
		return typeInfo;
	}
}
