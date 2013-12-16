package NJU.HouseWang.nju_eas_client.ui.MainUI.UserCenterUI;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Panel.CommonPanel;
import NJU.HouseWang.nju_eas_client.uiLogic.UserCenterUILogic;
import NJU.HouseWang.nju_eas_client.vo.Feedback;
import NJU.HouseWang.nju_eas_client.vo.StudentVO;
import NJU.HouseWang.nju_eas_client.vo.TeacherVO;

public class ShowSelfInfoPanel extends JPanel {
	private UserCenterUILogic logic = new UserCenterUILogic();
	private CommonPanel picPanel = new CommonPanel("userInfoPanel");
	private JLabel[][] lb = new JLabel[4][4];

	public ShowSelfInfoPanel() {
		setLayout(null);
		picPanel.setSize(700, 425);
		picPanel.setBounds(0, 0, 700, 425);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				lb[i][j] = new JLabel("", JLabel.CENTER);
				lb[i][j].setFont(new Font("微软雅黑", Font.PLAIN, 16));
				add(lb[i][j]);
			}
		}

		for (int i = 0; i < 4; i++) {
			lb[i][0].setBounds(200, 100 + 40 * i, 90, 40);
			lb[i][1].setBounds(300, 100 + 40 * i, 100, 40);
			lb[i][2].setBounds(410, 100 + 40 * i, 90, 40);
			lb[i][3].setBounds(510, 100 + 40 * i, 100, 40);
		}

		lb[0][0].setText("正在获取...");
		
		Object o = logic.showSelfInformation();
		if (o instanceof Feedback) {
			JOptionPane.showMessageDialog(null, ((Feedback) o).getContent());
		} else if(o instanceof StudentVO) {
			StudentVO stu = (StudentVO) o;
			lb[0][0].setText("学号");
			lb[0][1].setText(stu.id);
			lb[0][2].setText("姓名");
			lb[0][3].setText(stu.name);
			lb[1][0].setText("院系");
			lb[1][1].setText(stu.department);
			lb[1][2].setText("专业");
			lb[1][3].setText(stu.major);
			lb[2][0].setText("班级编号");
			lb[2][1].setText(stu.classNo);
			lb[2][2].setText("年级");
			lb[2][3].setText(stu.grade);
			lb[3][0].setText("学制");
			lb[3][1].setText(stu.duration);
			lb[3][2].setText("学籍状态");
			lb[3][3].setText(stu.enrollmentStatus);
		} else if (o instanceof TeacherVO) {
			TeacherVO tea = (TeacherVO) o;
			lb[0][0].setText("工号");
			lb[0][1].setText(tea.id);
			lb[0][2].setText("姓名");
			lb[0][3].setText(tea.name);
			lb[1][0].setText("类型");
			lb[1][1].setText(tea.userType);
			lb[1][2].setText("单位");
			lb[1][3].setText(tea.company);
		}
		add(picPanel);
	}
}
