package NJU.HouseWang.nju_eas_client.po.User;

import NJU.HouseWang.nju_eas_client.systemMessage.UserType;


public class TeacherPO extends UserPO {
	private String name;
	private String company;

	public TeacherPO() {
	}

	public TeacherPO(String id, String name, String company) {
		super(id, UserType.Teacher);
		this.name = name;
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "TeacherPO [name=" + name + ", company=" + company + "]";
	}

	@Override
	public String toCommand() {
		return id + "；" + type + "；" + name + "；" + company;
	}
}