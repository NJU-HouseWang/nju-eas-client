package NJU.HouseWang.nju_eas_client.ui.MainUI.ImportUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import NJU.HouseWang.nju_eas_client.uiLogic.ImportUILogic;
import NJU.HouseWang.nju_eas_client.vo.Feedback;

public class ImportProgressBar {
	private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	private ImportUILogic logic = new ImportUILogic();
	private JFrame frame = null;
	private JProgressBar progressbar;
	private JLabel label;
	private String itemName = null;
	private ArrayList<String> list = new ArrayList<String>();

	public ImportProgressBar(String itemName, ArrayList<String> list) {
		this.itemName = itemName;
		this.list = list;
		frame = new JFrame("上传进度");
		frame.setBounds((screen.width - 400) / 2, (screen.height - 100) / 2,
				400, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPanel = frame.getContentPane();
		label = new JLabel("", JLabel.CENTER);
		progressbar = new JProgressBar();
		progressbar.setOrientation(JProgressBar.HORIZONTAL);
		progressbar.setMinimum(0);
		progressbar.setMaximum(list.size());
		progressbar.setValue(0);
		progressbar.setStringPainted(true);
		progressbar.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int value = progressbar.getValue();
				if (e.getSource() == progressbar) {
					label.setText("正在添加第 " + Integer.toString(value) + " 条...");
				}
			}
		});
		progressbar.setPreferredSize(new Dimension(300, 20));
		progressbar.setBorderPainted(true);
		contentPanel.add(progressbar, BorderLayout.CENTER);
		contentPanel.add(label, BorderLayout.SOUTH);
		frame.setResizable(false);
	}

	public void startImport() {
		frame.setVisible(true);
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < list.size(); i++) {
					Feedback fb = null;
					if ((fb = logic.addItem(itemName, list.get(i))) == Feedback.OPERATION_SUCCEED) {
						progressbar.setValue(i + 1);
					} else {
						JOptionPane.showMessageDialog(null, "第" + (i + 1)
								+ "项导入失败：" + fb.getContent());
						break;
					}
				}
				frame.dispose();
				if (progressbar.getValue() == list.size()) {
					JOptionPane.showMessageDialog(null, "导入成功");
				}
			}
		}).start();
	}
}