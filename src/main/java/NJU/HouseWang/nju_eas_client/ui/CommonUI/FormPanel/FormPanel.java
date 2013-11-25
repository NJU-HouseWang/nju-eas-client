package NJU.HouseWang.nju_eas_client.ui.CommonUI.FormPanel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FormPanel extends JPanel {

	private JScrollPane scrollp = null;
	private DefaultTableModel dtm = null;
	private JTable table = null;
	private String[] head = null;
	private String[][] content = null;

	public FormPanel() {
		this.setLayout(null);
		scrollp = new JScrollPane();
		dtm = new DefaultTableModel(30, 5);
		table = new JTable(dtm);
		scrollp.setViewportView(table);
		scrollp.setSize(this.getSize());
		this.add(scrollp);
	}

	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		scrollp.setSize(this.getSize());
	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		scrollp.setSize(this.getSize());
	}

	public void setFormHead(String[] head) {
		this.head = head;
		dtm.setColumnCount(head.length);
		dtm.setColumnIdentifiers(head);
	}

	public void setFormContent(String[][] content) {
		this.content = content;
		dtm.setDataVector(content, head);
	}
	

	public String[] getHead() {
		return head;
	}


	public String[][] getContent() {
		return content;
	}


}
