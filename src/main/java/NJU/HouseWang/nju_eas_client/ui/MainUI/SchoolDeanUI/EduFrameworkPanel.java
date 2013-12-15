package NJU.HouseWang.nju_eas_client.ui.MainUI.SchoolDeanUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.CTable.CTable;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.FunctionBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.SubPanel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.FunctionBtn.FunctionBtn;
import NJU.HouseWang.nju_eas_client.uiLogic.SchoolDeanUILogic;

public class EduFrameworkPanel extends JPanel {
	private SchoolDeanUILogic logic = new SchoolDeanUILogic();
	private FunctionBar fbar = null;
	private FunctionBtn[] fBtn = new FunctionBtn[2];
	private SubPanel edufwp = null;
	private EduFrameworkMap map = null;
	private DefaultTableModel dtm = null;
	private CTable table = null;
	private JButton refreshBtn = new JButton();

	private String[][] content = new String[1][7];

	private String[] head = new String[7];

	// private String[] head = new String[] { "课程模块", "课程性质", "序列", "课程类型",
	// "课程名", "建议学分", "建议学期" };

	@SuppressWarnings("serial")
	public EduFrameworkPanel() {
		showEduFw();
		setLayout(null);
		setBackground(Color.white);
		fbar = new FunctionBar();
		fbar.setLocation(0, 0);
		fBtn[0] = new FunctionBtn("AddBtn");
		fBtn[1] = new FunctionBtn("DelBtn");
		for (int i = 0; i < fBtn.length; i++) {
			fbar.add(fBtn[i]);
		}
		add(fbar);

		edufwp = new SubPanel("当前框架策略", 740, 380);
		edufwp.setLocation(30, 70);

		refreshBtn.setBounds(3, 3, 22, 22);
		refreshBtn.setPreferredSize(new Dimension(22, 22));
		edufwp.getTopPanel().add(refreshBtn);

		map = new EduFrameworkMap(content);
		dtm = new DefaultTableModel(content.length, content[0].length) {
			public boolean isCellEditable(int indexRow, int indexColumn) {
				return false;
			}
		};

		table = new CTable(map, dtm);
		table.setEnabled(false);
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
		edufwp.getCenterPanel().setLayout(new BorderLayout());
		edufwp.getCenterPanel()
				.add(new JScrollPane(table), BorderLayout.CENTER);
		add(edufwp);
		setListener();
		for (int i = 0; i < content.length; i++) {
			for (int j = 0; j < content[i].length; j++) {
				dtm.setValueAt(content[i][j], i, j);
			}
		}
		dtm.setColumnIdentifiers(head);
	}

	private void setListener() {
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEduFw();
				for (int i = 0; i < content.length; i++) {
					for (int j = 0; j < content[i].length; j++) {
						dtm.setValueAt(content[i][j], i, j);
					}
				}
				dtm.setColumnIdentifiers(head);
			}
		});

		fBtn[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddEduFwUI();
			}
		});

		fBtn[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Feedback fb = logic.delEdufw();
				JOptionPane.showMessageDialog(null, fb.getContent());
			}
		});
	}

	private void showEduFw() {
		head = null;
		content = null;
		Object fb = logic.showEduFwHead();
		if (fb instanceof Feedback) {
			JOptionPane.showMessageDialog(null, ((Feedback) fb).getContent());
		} else if (fb instanceof String[]) {
			head = (String[]) fb;
			fb = logic.showEduFwContent();
			if (fb instanceof Feedback) {
				JOptionPane.showMessageDialog(null,
						((Feedback) fb).getContent());
			} else if (fb instanceof String[][]) {
				content = (String[][]) fb;
			}
		}
	}
}
