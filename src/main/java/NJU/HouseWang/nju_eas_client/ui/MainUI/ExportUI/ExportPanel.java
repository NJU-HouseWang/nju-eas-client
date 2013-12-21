package NJU.HouseWang.nju_eas_client.ui.MainUI.ExportUI;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.CommonBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Label.WaitingLabel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CommonTable;
import NJU.HouseWang.nju_eas_client.uiLogic.ExportUILogic;

public class ExportPanel extends JPanel {
	private ExportUILogic logic = new ExportUILogic();
	private final ExportPanel panel = this;
	private JTextField pathField = new JTextField();
	private CommonBtn cfBtn = new CommonBtn("选择路径");
	private DefaultTableModel dtm = new DefaultTableModel();
	private CommonTable table = new CommonTable(dtm);
	private JScrollPane scrollPanel = new JScrollPane(table);
	private CommonBtn finishBtn = new CommonBtn("确认导出");
	private WaitingLabel waitlb1 = new WaitingLabel();
	private WaitingLabel waitlb2 = new WaitingLabel();

	private String[] head = null;
	private String[][] content = null;

	public ExportPanel(String[] head, String[][] content) {
		this.head = head;
		this.content = content;
		setSize(700, 425);
		setBackground(Color.white);
		setLayout(null);
		pathField.setBounds(35, 15, 400, 30);
		pathField.setEditable(false);
		cfBtn.setBounds(435, 15, 80, 30);

		scrollPanel.setBounds(35, 62, 625, 300);

		dtm.setColumnIdentifiers(head);

		finishBtn.setBounds(498, 372, 100, 30);
		waitlb1.setBounds(325, 195, 42, 42);
		waitlb2.setBounds(600, 365, 42, 42);
		add(pathField);
		add(cfBtn);
		add(waitlb1);
		add(scrollPanel);
		add(finishBtn);
		add(waitlb2);
		setListener();
		waitlb1.setVisible(false);
		waitlb2.setVisible(false);
		dtm.setDataVector(content, head);
		finishBtn.setEnabled(true);
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
				int result = jfc.showSaveDialog(panel);
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = jfc.getSelectedFile();
					String path = file.getAbsolutePath();
					if (!path.endsWith(".xls")) {
						path += ".xls";
					}
					pathField.setText(path);
				}
			}
		});

		finishBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finishBtn.setEnabled(false);
				waitlb2.setVisible(true);
				new Thread(new Runnable() {
					public void run() {
						try {
							File f = logic.buildXls(head, content,
									pathField.getText());
							Desktop desktop = Desktop.getDesktop();
							desktop.open(f);
						} catch (IOException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "导出失败");
						}
						finishBtn.setEnabled(true);
						waitlb2.setVisible(false);
					}
				}).start();
			}
		});
	}
}
