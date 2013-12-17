package NJU.HouseWang.nju_eas_client.ui.MainUI.TeacherUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import NJU.HouseWang.nju_eas_client.uiLogic.TeacherUILogic;
import NJU.HouseWang.nju_eas_client.vo.CourseDetailVO;
import NJU.HouseWang.nju_eas_client.vo.Feedback;

public class EditMyCourseUI {
	private TeacherUILogic logic = new TeacherUILogic();
	private JFrame frame = null;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = 0;
	private int height = 0;
	private EditMyCoursePanel panel = new EditMyCoursePanel();
	private JTextArea t1 = panel.textArea;
	private JTextArea t2 = panel.textArea_1;
	private JTextArea t3 = panel.textArea_2;
	private JButton confirmBtn = panel.button;
	private JButton cancelBtn = panel.button_1;
	private String couId = null;
	private CourseDetailVO dt = new CourseDetailVO();

	public EditMyCourseUI(String couId, CourseDetailVO dt) {
		this.couId = couId;
		this.dt = dt;
		frame = new JFrame();
		frame.setTitle("修改项目： 我的课程");
		t1.setText(dt.introduction);
		t2.setText(dt.book);
		t3.setText(dt.syllabus);

		frame.add(panel, BorderLayout.CENTER);
		frame.setAlwaysOnTop(true);
		addListener();
		frame.pack();
		width = frame.getWidth();
		height = frame.getHeight();
		frame.setBounds(((int) screenSize.getWidth() - width) / 2,
				((int) screenSize.getHeight() - height) / 2 - 30, width, height);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public void addListener() {
		confirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (t1.getText().equals("")) {
					dt.introduction = "null";
				} else {
					dt.introduction = t1.getText();
				}
				if (t2.getText().equals("")) {
					dt.book = "null";
				} else {
					dt.book = t2.getText();
				}
				if (t3.getText().equals("")) {
					dt.syllabus = "null";
				} else {
					dt.syllabus = t3.getText();
				}
				Feedback fb = logic.editCourseDetail(logic.showCurrentTerm(),
						couId, dt);
				JOptionPane.showMessageDialog(null, fb.getContent());
				frame.setVisible(false);
				frame.dispose();
			}
		});

		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
	}
}
