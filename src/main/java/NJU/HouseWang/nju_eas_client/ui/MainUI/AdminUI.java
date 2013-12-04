/*
 * 文件名：AdminUI.java
 * 创建者：王鑫
 * 创建时间：2013-10-16
 * 最后修改：王鑫
 * 修改时间：2013-11-02
 */
package NJU.HouseWang.nju_eas_client.ui.MainUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.CommonFrame;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.FunctionBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.TitleBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.FunctionBtn.FunctionBtn;
import NJU.HouseWang.nju_eas_client.ui.SubUI.AddItemUI;
import NJU.HouseWang.nju_eas_client.ui.SubUI.EditItemUI;
import NJU.HouseWang.nju_eas_client.uiLogic.AdminUILogic;

/*
 * 类：AdminUI
 * 
 */
public class AdminUI {
	private AdminUILogic logic = null;
	private CommonFrame frame = null;
	private TitleBar tbar = null;
	private FunctionBar fbar = null;
	private FunctionBtn[] fBtn = new FunctionBtn[5];
	private JComboBox<String> listChooser = null;
	private JTextField searchtf = null;
	private JButton searchBtn = null;

	private JPanel tablep = null;
	private JScrollPane scrollp = null;
	private DefaultTableModel dtm = null;
	private JTable table = null;

	private String[] head = null;
	private String[][] content = null;

	public AdminUI(String userName) {
		logic = new AdminUILogic();
		frame = new CommonFrame("AdminFrame");
		tbar = new TitleBar(userName);
		fbar = new FunctionBar();
		fbar.setLocation(30, 100);

		fBtn[0] = new FunctionBtn("AddBtn");
		fBtn[1] = new FunctionBtn("ModifyBtn");
		fBtn[2] = new FunctionBtn("DelBtn");
		fBtn[3] = new FunctionBtn("ImportBtn");
		fBtn[4] = new FunctionBtn("ExportBtn");

		listChooser = new JComboBox<String>();
		listChooser.setBounds(166, 167, 134, 22);
		listChooser.addItem("login_list");
		listChooser.addItem("teacher_list");
		listChooser.addItem("student_list");
		listChooser.addItem("log_list");
		listChooser.addItem("states_list");

		searchtf = new JTextField();
		searchtf.setBounds(627, 168, 130, 20);
		searchtf.setBorder(null);

		searchBtn = new JButton();
		searchBtn.setBounds(759, 167, 22, 22);

		tablep = new JPanel();
		tablep.setBounds(72, 210, 712, 381);
		tablep.setLayout(null);

		dtm = new DefaultTableModel(30, 5);
		table = new JTable(dtm);
		table.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		table.getTableHeader().setFont(new Font("微软雅黑", Font.BOLD, 12));
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);

		scrollp = new JScrollPane();
		scrollp.setViewportView(table);
		scrollp.setSize(tablep.getSize());
		tablep.add(scrollp);

		for (int i = 0; i < 5; i++) {
			fbar.add(fBtn[i]);
		}

		frame.add(tbar);
		frame.add(fbar);
		frame.add(listChooser);
		frame.add(searchtf);
		frame.add(searchBtn);
		frame.add(tablep);
		setListener();
		frame.setVisible(true);
		showForm((String) listChooser.getSelectedItem());
		fBtn[0].setEnabled(false);
		fBtn[1].setEnabled(true);
		fBtn[2].setEnabled(false);
		fBtn[3].setEnabled(false);
		fBtn[4].setEnabled(false);
	}

	private void setListener() {
		listChooser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String listName = (String) listChooser.getSelectedItem();
				showForm(listName);

				// 设置按钮状态
				switch (listName) {
				case "login_list":
				case "status_list":
					fBtn[0].setEnabled(false);
					fBtn[1].setEnabled(true);
					fBtn[2].setEnabled(false);
					fBtn[3].setEnabled(false);
					fBtn[4].setEnabled(false);
					break;
				case "student_list":
				case "teacher_list":
					fBtn[0].setEnabled(true);
					fBtn[1].setEnabled(true);
					fBtn[2].setEnabled(true);
					fBtn[3].setEnabled(false);
					fBtn[4].setEnabled(false);
					break;
				default:
					fBtn[0].setEnabled(false);
					fBtn[1].setEnabled(false);
					fBtn[2].setEnabled(false);
					fBtn[3].setEnabled(false);
					fBtn[4].setEnabled(false);
				}
			}
		});
		// searchBtn.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// String listName = (String) listChooser.getSelectedItem();
		// showForm(listName);
		// }
		// });
		fBtn[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String listName = (String) listChooser.getSelectedItem();
				new AddItemUI(listName.split("_")[0], head);
			}
		});
		fBtn[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String listName = (String) listChooser.getSelectedItem();
				int selectRowNum = table.getSelectedRow();
				if (selectRowNum != -1) {
					String[] origin = new String[table.getColumnCount()];
					for (int i = 0; i < origin.length; i++) {
						origin[i] = (String) table.getValueAt(selectRowNum, i);
					}
					new EditItemUI(listName.split("_")[0], head, origin);
				} else {
					showFeedback(Feedback.SELECTION_ERROR);
				}
			}
		});
		fBtn[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String listName = (String) listChooser.getSelectedItem();
				int selectRowNum = table.getSelectedRow();
				if (selectRowNum != -1) {
					System.out.println(table.getValueAt(selectRowNum, 0));
					logic.delItem(listName.split("_")[0],
							(String) table.getValueAt(selectRowNum, 0));
				} else {
					showFeedback(Feedback.SELECTION_ERROR);
				}
			}
		});
	}

	private void showForm(String listName) {
		Object o1 = logic.showTableHead(listName);
		if (o1 instanceof Feedback) {
			showFeedback((Feedback) o1);
		} else {
			head = (String[]) logic.showTableHead(listName);
			Object o2 = logic.showTableContent(listName);
			if (o2 instanceof Feedback) {
				showFeedback((String) o2);
			} else {
				content = (String[][]) o2;
				dtm.setDataVector(content, head);
				table.updateUI();
			}
		}
	}

	public void showFeedback(String feedback) {
		showFeedback(Feedback.valueOf(feedback));
	}

	public void showFeedback(Feedback feedback) {
		JOptionPane.showMessageDialog(frame, feedback.getContent());
	}

	// public static void main(String[] args) {
	// new AdminFrame("Admin");
	// }
}
