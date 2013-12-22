package NJU.HouseWang.nju_eas_client.vo;

public class StudentVO {
	public String id;
	public String name;
	public String department;
	public String major;
	public String grade;
	public String classNo;
	public String duration;
	public String enrollmentStatus;

	public StudentVO() {

	}

	public StudentVO(String[] str) {
		id = str[0];
		name = str[1];
		department = str[2];
		major = str[3];
		grade = str[4];
		classNo = str[5];
		duration = str[6];
		enrollmentStatus = str[7];
	}
}
