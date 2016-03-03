package base.zfarming.servlet.students;

import java.util.Map;

import base.zfarming.dao.BaseDao;

public class GetEntityInfo {
	public static final String COURSE="course";
	public static final String UNIVERSITY="university";
	public static final String DEPARTMENT="department";
	public static final String USER="user";
	private static BaseDao baseDao = new BaseDao();
	public static Map<String,Object> getCourseInfo(int courseID){
		String sql = "select * from main_course where id="+courseID;
		return baseDao.get(sql);
	}
	public static Map<String, Object> getDepartmentInfo(int departID){
		String sql = "select * from main_department where id="+departID;
		return baseDao.get(sql);
	}
	public static Map<String,Object> getUniversityInfo(int universityID){
		String sql = "select * from main_university where id="+universityID;
		return baseDao.get(sql);
	}
	public static Map<String,Object> getUserInfo(int userID){
		String sql = "select * from sys_user where id ="+userID;
		return baseDao.get(sql);
	}
}
