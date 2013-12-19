package NJU.HouseWang.nju_eas_client.vo;

public class TermVO {
	public String firstYear;
	public String secondYear;
	public String termth;

	public TermVO() {
	}

	public TermVO(String termInfo) {
		firstYear = termInfo.split("-")[0];
		secondYear = (Integer.parseInt(termInfo.split("-")[0]) + 1) + "";
		termth = termInfo.split("第")[1].charAt(0) + "";
	}

	@Override
	public String toString() {
		return firstYear + "-" + secondYear + "学年 第" + termth + "学期";
	}

}
