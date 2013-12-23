package NJU.HouseWang.nju_eas_client.ui.MainUI.SchoolDeanUI.AddFwGuideUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CommonTable;

public class CoursePanel extends JPanel {
	private int typeNum = 0;
	private ArrayList<String> typeInfo = null;
	private ArrayList<String> courseInfo = new ArrayList<String>();

	private JPanel p3main = new JPanel();
	private JScrollPane p3sp = new JScrollPane(p3main);
	private JLabel[] couLb;

	private DefaultTableModel[] couDtm = null;
	private CommonTable[] couTable = null;
	private JScrollPane[] couScroll = null;

	public CoursePanel(ArrayList<String> typeInfo) {
		typeNum = typeInfo.size();
		this.typeInfo = typeInfo;
		couDtm = new DefaultTableModel[typeNum];
		couTable = new CommonTable[typeNum];
		couScroll = new JScrollPane[typeNum];
		couLb = new JLabel[typeNum];

		setLayout(new BorderLayout());
		p3sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		p3sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		p3main.setPreferredSize(new Dimension(595, typeNum * 200));
		p3main.setLayout(new BoxLayout(p3main, BoxLayout.Y_AXIS));
		for (int i = 0; i < typeNum; i++) {
			couDtm[i] = new DefaultTableModel(16, 3);
			couDtm[i].setColumnIdentifiers(new String[] { "课程名称", "最低学分",
					"最高学分" });
			couLb[i] = new JLabel(typeInfo.get(i), JLabel.LEFT);
			couLb[i].setFont(new Font("微软雅黑", Font.PLAIN, 20));
			couLb[i].setPreferredSize(new Dimension(595, 50));
			p3main.add(couLb[i]);
			couTable[i] = new CommonTable(couDtm[i]);
			couTable[i].setPreferredScrollableViewportSize(new Dimension(400,
					150));
			DefaultTableCellRenderer r = new DefaultTableCellRenderer();
			r.setHorizontalAlignment(JLabel.CENTER);
			couTable[i].setDefaultRenderer(Object.class, r);
			couTable[i].setRowHeight(25);
			couScroll[i] = new JScrollPane(couTable[i]);
			p3main.add(couScroll[i]);
		}

		add(p3sp, BorderLayout.CENTER);
	}

	public boolean collectCourseInfo() {
		courseInfo.clear();
		for (int i = 0; i < typeInfo.size(); i++) {
			int rowPoint = 0;
			while (true) {
				String line = "";
				for (int j = 0; j < 3; j++) {
					String cell = null;
					if (couTable[i].getValueAt(rowPoint, j) == null
							|| couTable[i].getValueAt(rowPoint, j).equals("")) {
						if (rowPoint == 0) {
							cell = "null";
							line += cell + "；";
						} else {
							line = null;
						}
					} else {
						cell = (String) couTable[i].getValueAt(rowPoint, j);
						line += cell + "；";
					}
				}
				if (line != null) {
					System.out.println("Get Course Info:"
							+ new String(typeInfo.get(i)) + line);
					courseInfo.add(new String(typeInfo.get(i)) + line);
				} else {
					break;
				}
				rowPoint++;
			}
		}
		return true;
	}

	public ArrayList<String> getCourseInfoList() {
		return courseInfo;
	}
}
