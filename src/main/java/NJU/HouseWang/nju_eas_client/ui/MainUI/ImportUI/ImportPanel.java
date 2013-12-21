package NJU.HouseWang.nju_eas_client.ui.MainUI.ImportUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.CommonBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Label.WaitingLabel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CommonTable;
import NJU.HouseWang.nju_eas_client.uiLogic.ImportUILogic;

public class ImportPanel extends JPanel {
	private ImportUILogic logic = new ImportUILogic();

	private final ImportPanel panel = this;
	private JTextField pathField = new JTextField();
	private CommonBtn cfBtn = new CommonBtn("选择文件");
	private DefaultTableModel dtm = new DefaultTableModel();
	private CommonTable table = new CommonTable(dtm);
	private JScrollPane scrollPanel = new JScrollPane(table);
	private JLabel notionlb = new JLabel();
	private CommonBtn finishBtn = new CommonBtn("确认导入");
	private WaitingLabel waitlb1 = new WaitingLabel();
	private WaitingLabel waitlb2 = new WaitingLabel();

	private JRadioButton r1 = new JRadioButton("无表头");
	private JRadioButton r2 = new JRadioButton("有表头");
	private ButtonGroup rg = new ButtonGroup();

	private ArrayList<String> l = new ArrayList<>();
	private String itemName = null;
	private String tmpHead = null;
	private String[] head = null;
	private String[][] content = null;
	private int importPoint = 0;

	public ImportPanel(String itemName, String[] head) {
		this.itemName = itemName;
		this.head = head;
		setSize(700, 425);
		setBackground(Color.white);
		setLayout(null);
		pathField.setBounds(35, 15, 400, 30);
		pathField.setEditable(false);
		cfBtn.setBounds(435, 15, 80, 30);

		r1.setBackground(Color.white);
		r2.setBackground(Color.white);
		r1.setBounds(530, 23, 70, 15);
		r2.setBounds(600, 23, 70, 15);
		rg.add(r1);
		rg.add(r2);
		r1.setSelected(true);

		scrollPanel.setBounds(35, 62, 625, 300);

		dtm.setColumnIdentifiers(head);

		notionlb.setText("Notion：导入前请确保上表中表头与数据相对应。");
		notionlb.setBounds(90, 375, 380, 20);
		finishBtn.setBounds(498, 372, 100, 30);
		waitlb1.setBounds(325, 195, 42, 42);
		waitlb2.setBounds(600, 365, 42, 42);
		add(r1);
		add(r2);
		add(pathField);
		add(cfBtn);
		add(waitlb1);
		add(scrollPanel);
		add(notionlb);
		add(finishBtn);
		add(waitlb2);
		setListener();
		waitlb1.setVisible(false);
		waitlb2.setVisible(false);
		finishBtn.setEnabled(false);
	}

	private void setListener() {
		cfBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				jfc.setFileFilter(new FileFilter() {
					public String getDescription() {
						return "xls文件";
					}

					public boolean accept(File f) {
						if (f.getName().endsWith(".xls") || f.isDirectory()) {
							return true;
						}
						return false;
					}
				});
				int result = jfc.showOpenDialog(panel);
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = jfc.getSelectedFile();
					pathField.setText(file.getAbsolutePath());
					try {
						l = logic.readXls(file.getAbsolutePath());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				showTable();
			}
		});
		r1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showTable();
			}
		});
		r2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showTable();
			}
		});
		finishBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finishBtn.setEnabled(false);
				new Thread(new Runnable() {
					public void run() {
						ImportProgressBar ipb = new ImportProgressBar(itemName,
								l);
						ipb.startImport();
						finishBtn.setEnabled(true);
					}
				}).start();
			}
		});
	}

	public void showTable() {
		waitlb1.setVisible(true);
		if (!l.isEmpty()) {
			if (r2.isSelected()) {
				if (tmpHead == null) {
					tmpHead = l.get(0);
					l.remove(0);
				} else if (l.get(0).equals(tmpHead)) {
					l.remove(0);
				}
			} else {
				if (tmpHead != null && !l.get(0).equals(tmpHead)) {
					l.add(0, tmpHead);
				}
			}
			content = new String[l.size()][l.get(0).split("；").length];
			for (int i = 0; i < l.size(); i++) {
				content[i] = l.get(i).split("；");
			}
			dtm.setDataVector(content, head);
			finishBtn.setEnabled(true);
		} else {
			finishBtn.setEnabled(false);
		}
		waitlb1.setVisible(false);
	}

	public void setItemName(String itemName) {

	}

	public void setTableHead(String[] head) {

	}
}
