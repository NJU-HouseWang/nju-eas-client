package NJU.HouseWang.nju_eas_client.uiLogicService;

public class TeacherVO {
	public String id;
	public String userType;
	public String name;
	public String company;

	public TeacherVO() {

	}

	public TeacherVO(String[] str) {
		id = str[0];
		userType = str[1];
		name = str[2];
		company = str[3];
	}

}
