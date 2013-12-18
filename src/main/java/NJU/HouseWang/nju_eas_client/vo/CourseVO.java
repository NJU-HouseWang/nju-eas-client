package NJU.HouseWang.nju_eas_client.vo;

public class CourseVO {
	public String couId = null;
	public String couName = null;

	public CourseVO() {
	}

	public CourseVO(String couId, String couName) {
		this.couId = couId;
		this.couName = couName;
	}

	public String toString() {
		return couName;
	}
}
