/*
 * 文件名：UserTypeVO.java
 * 创建者：王鑫
 * 创建时间：2013-11-02
 * 最后修改：王鑫
 * 修改时间：
 */
package NJU.HouseWang.nju_eas_client.vo;

/*
 * 类：UserTypeVO
 * 
 */
public class UserTypeVO {
	public String name_en;
	public String name_cn;

	public UserTypeVO(String en, String cn) {
		name_en = en;
		name_cn = cn;
	}

	public String toString() {
		return name_cn;
	}

}