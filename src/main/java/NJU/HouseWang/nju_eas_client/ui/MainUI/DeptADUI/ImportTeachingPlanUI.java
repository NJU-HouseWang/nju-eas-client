package NJU.HouseWang.nju_eas_client.ui.MainUI.DeptADUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.CommonBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.SmallMenuBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Frame.SmallCommonFrame;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Label.WaitingLabel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CommonTable;
import NJU.HouseWang.nju_eas_client.uiLogic.DeptADUILogic;
import NJU.HouseWang.nju_eas_client.uiLogic.ImportUILogic;
import NJU.HouseWang.nju_eas_client.vo.Feedback;

public class ImportTeachingPlanUI {
	private int FUNC_NUM = 1;
	private SmallCommonFrame frame = null;
	private JPanel menuBar = null;
	private ButtonGroup btnG = null;
	private CardLayout cardL = null;
	private JPanel cardP = null;
	private String[] funcName = { "导入" };
	private SmallMenuBtn[] menuBtn = new SmallMenuBtn[FUNC_NUM];
	private JPanel[] switchPane = new JPanel[FUNC_NUM];

	public ImportTeachingPlanUI() {
		frame = new SmallCommonFrame("IOMgrFrame");
		menuBar = new JPanel();
		cardL = new CardLayout();
		cardP = new JPanel(cardL);
		cardP.setBounds(30, 105, 700, 425);
		btnG = new ButtonGroup();
		menuBar.setBounds(30, 60, 700, 45);
		menuBar.setBackground(Color.getHSBColor((float) 0.617, (float) 1,
				(float) 0.24));

		FlowLayout lf = (FlowLayout) menuBar.getLayout();
		lf.setHgap(0);
		lf.setVgap(0);
		lf.setAlignment(FlowLayout.LEFT);

		switchPane[0] = new ImportPanel();
		for (int i = 0; i < FUNC_NUM; i++) {
			menuBtn[i] = new SmallMenuBtn(funcName[i]);
			menuBtn[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cardL.show(cardP,
							((SmallMenuBtn) e.getSource()).getFuncName());
				}
			});
			btnG.add(menuBtn[i]);
			menuBar.add(menuBtn[i]);
			cardP.add(switchPane[i], funcName[i]);
		}

		menuBtn[0].setSelected(true);
		cardL.show(cardP, funcName[0]);
		frame.add(cardP);
		frame.add(menuBar);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new ImportTeachingPlanUI();
	}
}

class ImportPanel extends JPanel {
	private ImportUILogic ilogic = new ImportUILogic();
	private DeptADUILogic slogic = new DeptADUILogic();
	private final ImportPanel panel = this;
	private JTextField pathField = new JTextField();
	private JTextField pathFieldf = new JTextField();
	private CommonBtn getBtn = new CommonBtn("获取模板");
	private CommonBtn cfBtn = new CommonBtn("选择列表");
	private CommonBtn cfBtnf = new CommonBtn("选择文件");
	private DefaultTableModel dtm = new DefaultTableModel();
	private CommonTable table = new CommonTable(dtm);
	private JScrollPane scrollPanel = new JScrollPane(table);
	private JLabel notionlb = new JLabel();
	private CommonBtn finishBtn = new CommonBtn("确认导入");
	private WaitingLabel waitlb1 = new WaitingLabel();
	private WaitingLabel waitlb2 = new WaitingLabel();

	private ArrayList<String> l = new ArrayList<>();
	private String[] head = null;
	private String[][] content = null;

	public ImportPanel() {
		head = (String[]) slogic.showTPHead_import();
		setSize(700, 425);
		setBackground(Color.white);
		setLayout(null);

		getBtn.setBounds(35, 15, 90, 30);

		pathField.setBounds(125, 15, 440, 30);
		pathField.setEditable(false);
		cfBtn.setBounds(565, 15, 90, 30);

		pathFieldf.setBounds(35, 50, 530, 30);
		pathFieldf.setEditable(false);
		cfBtnf.setBounds(565, 50, 90, 30);

		scrollPanel.setBounds(35, 102, 620, 250);

		dtm.setColumnIdentifiers(head);

		notionlb.setText("Notion：导入前请确保上表中表头与数据相对应。");
		notionlb.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		notionlb.setBounds(90, 375, 380, 20);
		finishBtn.setBounds(498, 372, 100, 30);
		waitlb1.setBounds(325, 195, 42, 42);
		waitlb2.setBounds(600, 365, 42, 42);
		add(getBtn);
		add(pathField);
		add(cfBtn);
		add(pathFieldf);
		add(cfBtnf);
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

	public static void main(String[] args) {
		new ImportTeachingPlanUI();
	}

	private void setListener() {
		getBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Feedback fb = slogic.downloadTPTemplate();
				if (fb == Feedback.OPERATION_SUCCEED) {
					JOptionPane.showMessageDialog(null,
							"请将打开的Xls文件另存为。对其编辑后在进行导入操作。");
				} else {
					JOptionPane.showMessageDialog(null, "模板获取失败");
				}
			}
		});
		cfBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LookAndFeel look = UIManager.getLookAndFeel();
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
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
						l = ilogic.readXls(file.getAbsolutePath());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				try {
					UIManager.setLookAndFeel(look);
				} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
				showTable();
			}
		});

		cfBtnf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LookAndFeel look = UIManager.getLookAndFeel();
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				jfc.setFileFilter(new FileFilter() {
					public String getDescription() {
						return "文本文件";
					}

					public boolean accept(File f) {
						if (f.getName().endsWith(".pdf")
								|| f.getName().endsWith(".doc")
								|| f.getName().endsWith(".docx")
								|| f.isDirectory()) {
							return true;
						}
						return false;
					}
				});
				int result = jfc.showOpenDialog(panel);
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = jfc.getSelectedFile();
					pathFieldf.setText(file.getAbsolutePath());
				}

				if (!pathFieldf.getText().equals("") && !l.isEmpty()) {
					finishBtn.setEnabled(true);
				} else {
					finishBtn.setEnabled(false);
				}
				try {
					UIManager.setLookAndFeel(look);
				} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
			}
		});

		finishBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finishBtn.setEnabled(false);
				waitlb2.setVisible(true);
				new Thread(new Runnable() {
					public void run() {
						ArrayList<String> list = new ArrayList<String>();
						for (int i = 0; i < table.getRowCount(); i++) {
							String s = "";
							for (int j = 0; j < head.length; j++) {
								String tmp = (String) dtm.getValueAt(i, j);
								if (tmp == null || tmp.equals("")) {
									tmp = "null";
								}
								s += tmp + "；";
							}
							list.add(s);
						}
						Feedback fb = slogic.addTeachingPlan(list);
						if (fb == Feedback.OPERATION_SUCCEED) {
							fb = slogic.uploadTeachingPlan(new File(pathFieldf
									.getText()));
						}
						JOptionPane.showMessageDialog(null, fb.getContent());
						finishBtn.setEnabled(true);
						waitlb2.setVisible(false);
					}
				}).start();
			}
		});
	}

	public void showTable() {
		waitlb1.setVisible(true);
		l.remove(0);
		if (!l.isEmpty()) {
			content = new String[l.size()][l.get(0).split("；").length];
			for (int i = 0; i < l.size(); i++) {
				content[i] = l.get(i).split("；");
			}
			for (int i = 0; i < l.size(); i++) {
				for (int j = 0; j < content[i].length; j++) {
					if (content[i][j].equals("null")) {
						content[i][j] = "";
					}
				}
			}
			dtm.setDataVector(content, head);
			if (!pathFieldf.getText().equals("")) {
				finishBtn.setEnabled(true);
			} else {
				finishBtn.setEnabled(false);
			}
		} else {
			finishBtn.setEnabled(false);
		}
		waitlb1.setVisible(false);
	}
}
