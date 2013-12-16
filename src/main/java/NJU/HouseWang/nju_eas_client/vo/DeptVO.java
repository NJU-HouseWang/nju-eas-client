package NJU.HouseWang.nju_eas_client.vo;

public class DeptVO {
	public String deptId;
	public String deptName;

	public DeptVO() {
	}

	public DeptVO(String str) {
		String[] sp = str.split("；");
		deptId = sp[0];
		deptName = sp[1];
	}

	public DeptVO(String deptId, String deptName) {
		this.deptId = deptId;
		this.deptName = deptName;
	}

	public String toString() {
		return deptName;
	}
}
