package NJU.HouseWang.nju_eas_client.po.Msg;

import NJU.HouseWang.nju_eas_client.po.DataPOService;

public class CoursePO implements DataPOService {
	private String id;// 课程编号
	private String name;// 课程名称
	private String module;// 所属模块
	private String type;// 课程类别
	private String nature;// 课程性质
	private int credit;// 学分
	private int period;// 学时
	private String department;// 院系
	private String teacher;// 授课老师
	private String time;// 上课时间
	private String place;// 上课地点
	private String introduction;// 介绍
	private String book;// 参考书目
	private String syllabus;// 教学大纲

	public CoursePO() {
	}

	public CoursePO(String courseNo, String name) {
		super();
		this.id = courseNo;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String courseNo) {
		this.id = courseNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(String syllabus) {
		this.syllabus = syllabus;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

}