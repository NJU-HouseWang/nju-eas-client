package NJU.HouseWang.nju_eas_client.ui.MainUI.SchoolDeanUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.SubPanel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CommonTable;

public class InfoListPanel extends JPanel {
	private SubPanel edufwp = new SubPanel("列表：", 740, 420);
	private JComboBox<String> listChooser = new JComboBox<String>();
	private JComboBox<String> deptChooser = new JComboBox<String>();
	private JComboBox<String> yearChooser = new JComboBox<String>();
	private JComboBox<String> gradeChooser = new JComboBox<String>();
	private JLabel deptlb = new JLabel("院系：");
	private JLabel yearlb = new JLabel("学年：");
	private JLabel gradelb = new JLabel("年级：");

	private DefaultTableModel dtm = new DefaultTableModel(40, 5);
	private CommonTable infoTable = new CommonTable(dtm);

	public InfoListPanel() {
		setLayout(null);
		setBackground(Color.white);

		edufwp.setLocation(30, 30);

		listChooser.setPreferredSize(new Dimension(120, 20));
		listChooser.setFont(new Font("微软雅黑", Font.BOLD, 14));
		deptChooser.setPreferredSize(new Dimension(120, 20));
		deptChooser.setFont(new Font("微软雅黑", Font.BOLD, 14));
		yearChooser.setPreferredSize(new Dimension(120, 20));
		yearChooser.setFont(new Font("微软雅黑", Font.BOLD, 14));
		gradeChooser.setPreferredSize(new Dimension(120, 20));
		gradeChooser.setFont(new Font("微软雅黑", Font.BOLD, 14));

		deptlb.setFont(new Font("微软雅黑", Font.BOLD, 14));
		deptlb.setForeground(Color.white);
		yearlb.setFont(new Font("微软雅黑", Font.BOLD, 14));
		yearlb.setForeground(Color.white);
		gradelb.setFont(new Font("微软雅黑", Font.BOLD, 14));
		gradelb.setForeground(Color.white);

		listChooser.addItem("学生列表");
		listChooser.addItem("老师列表");
		listChooser.addItem("课程列表");
		listChooser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				if (cb.getSelectedItem().equals("课程列表")) {
					yearlb.setVisible(true);
					gradelb.setVisible(true);
					yearChooser.setVisible(true);
					gradeChooser.setVisible(true);
				} else {
					yearlb.setVisible(false);
					gradelb.setVisible(false);
					yearChooser.setVisible(false);
					gradeChooser.setVisible(false);
				}
			}
		});

		edufwp.getTopPanel().add(listChooser);
		edufwp.getTopPanel().add(deptlb);
		edufwp.getTopPanel().add(deptChooser);
		edufwp.getTopPanel().add(yearlb);
		edufwp.getTopPanel().add(yearChooser);
		edufwp.getTopPanel().add(gradelb);
		edufwp.getTopPanel().add(gradeChooser);
		yearlb.setVisible(false);
		gradelb.setVisible(false);
		yearChooser.setVisible(false);
		gradeChooser.setVisible(false);

		edufwp.getCenterPanel().setLayout(new BorderLayout());
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		infoTable.setDefaultRenderer(Object.class, r);

		edufwp.getCenterPanel().add(new JScrollPane(infoTable),
				BorderLayout.CENTER);
		add(edufwp);

	}
}