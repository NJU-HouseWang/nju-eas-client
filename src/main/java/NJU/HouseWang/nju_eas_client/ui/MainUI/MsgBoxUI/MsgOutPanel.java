package NJU.HouseWang.nju_eas_client.ui.MainUI.MsgBoxUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CommonTable;
import NJU.HouseWang.nju_eas_client.uiLogic.MsgBoxUILogic;
import NJU.HouseWang.nju_eas_client.vo.MessageVO;

public class MsgOutPanel extends JPanel {
	MsgBoxUILogic logic = new MsgBoxUILogic();
	ArrayList<MessageVO> list = new ArrayList<MessageVO>();

	private DefaultTableModel dtm = new DefaultTableModel(0, 2) {
		public boolean isCellEditable(int arg0, int arg1) {
			return false;
		}
	};
	private CommonTable table = new CommonTable(dtm);
	private JScrollPane js = new JScrollPane(table);

	public MsgOutPanel() {
		setLayout(new BorderLayout());
		setBackground(Color.black);
		table.setShowVerticalLines(false);
		table.setSelectionMode(0);
		dtm.setColumnIdentifiers(new String[] { "收件人", "主题" });
		js.setPreferredSize(new Dimension(548, 423));
		add(js, BorderLayout.CENTER);
		setListener();
		showTable();
	}

	private void setListener() {
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (table.getSelectedRowCount() == 1) {
						MessageVO msg = list.get(table.getSelectedRow());
						new ReadMsgUI(1, msg);
					}
				}
			}
		});
	}

	public void showTable() {
		for (int i = 0; i < table.getRowCount(); i++) {
			dtm.removeRow(0);
		}
		list = logic.showMsgList(1);
		for (MessageVO m : list) {
			dtm.insertRow(
					0,
					new String[] {
							m.recipientId + " ("
									+ logic.showUserName(m.recipientId) + ")",
							m.title });
		}
	}
}
