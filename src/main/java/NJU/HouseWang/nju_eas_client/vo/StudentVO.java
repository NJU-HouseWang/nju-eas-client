package NJU.HouseWang.nju_eas_client.vo;

public class StudentVO {
	public String id;
	public String userType;
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
		userType = str[1];
		name = str[2];
		department = str[3];
		major = str[4];
		grade = str[5];
		classNo = str[6];
		duration = str[7];
		enrollmentStatus = str[8];
	}
}
