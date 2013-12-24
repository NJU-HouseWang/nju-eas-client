package NJU.HouseWang.nju_eas_client.vo;

public class TPDeptVO {
	public String deptId;
	public String deptName;
	public boolean isCommitted;
	public int tpState;
	public String fileName;

	public TPDeptVO() {
	}

	public TPDeptVO(String str) {
		String[] sp = str.split("；");
		deptId = sp[0];
		deptName = sp[0];
		isCommitted = Boolean.valueOf(sp[1]).booleanValue();
		tpState = Integer.parseInt(sp[2]);
		fileName = sp[3];
	}

	public TPDeptVO(String deptId, String deptName) {
		this.deptId = deptId;
		this.deptName = deptName;
	}

	public String toString() {
		return deptName;
	}
}
