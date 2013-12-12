package NJU.HouseWang.nju_eas_client.vo;

public class DeptVO {
	public String deptId;
	public String deptName;
	public boolean isCommitted;
	public int tpState;
	public String fileName;

	public DeptVO() {
	}

	public DeptVO(String str) {
		String[] sp = str.split("；");
		deptId = sp[0];
		deptName = sp[1];
		isCommitted = Boolean.valueOf(sp[2]).booleanValue();
		tpState = Integer.parseInt(sp[3]);
		fileName = sp[4];
	}

	public String toString() {
		return deptName;
	}
}
