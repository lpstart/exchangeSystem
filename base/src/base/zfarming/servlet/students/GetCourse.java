package base.zfarming.servlet.students;

import java.util.List;
import java.util.Map;

import base.zfarming.dao.BaseDao;

public class GetCourse {
	private static BaseDao baseDao = new BaseDao();

	/**
	 * 根据学生id和分页信息（包括从第几条数据开始，要几条数据），得到应该返回的该学生的正在选的本学校的课的相关信息
	 * 
	 * @param studentID
	 * @param start
	 * @param numbers
	 * @return
	 */
	public static List<Map<String, Object>> getSelectingNoExchange(
			int studentID, int start, int numbers) {
		return getSelectingCourses(studentID, start, numbers, 0);
	}

	/**
	 * 根据学生id得到该学生在该学校正在选的课的数目
	 * 
	 * @param studentID
	 * @return
	 */
	public static long getTotalNumberSelectingCoursesNoExchange(int studentID) {
		return getSelectingNumberCourses(studentID, 0);
	}

	/**
	 * 根据学生id得到该学生在其他学校正在选的课的数目
	 * 
	 * @param studentID
	 * @return
	 */
	public static long getTotalNumberSelectingCoursesExchange(int studentID) {
		return getSelectingNumberCourses(studentID, 1);
	}

	/**
	 * 根据学生id和分页信息（包括从第几条数据开始，要几条数据），得到应该返回的该学生的正在选的其他学校的课的相关信息
	 * 
	 * @param studentID
	 * @param start
	 * @param numbers
	 * @return
	 */
	public static List<Map<String, Object>> getSelectingExchange(int studentID,
			int start, int numbers) {
		return getSelectingCourses(studentID, start, numbers, 1);
	}

	/**
	 * 根据学生id和分页信息（包括从第几条数据开始，要几条数据），得到应该返回的该学生的已经成功选出的本学校的课的相关信息
	 * 
	 * @param studentID
	 * @param start
	 * @param numbers
	 * @return
	 */
	public static List<Map<String, Object>> getNoExchange(int studentID,
			int start, int numbers) {
		return getSuccessSelectedCourses(studentID, start, numbers, 0);
	}

	/**
	 * 根据学生id得到该学生在该学校一共选了多少门课
	 * 
	 * @param studentID
	 * @return
	 */
	public static long getTotalNumberCoursesNoExchange(int studentID) {
		return getSuccessSelectedNumberCourses(studentID, 0);
	}

	/**
	 * 根据学生id得到该学生在其他学校一共选了多少门课
	 * 
	 * @param studentID
	 * @return
	 */
	public static long getTotalNumberCoursesExchange(int studentID) {
		return getSuccessSelectedNumberCourses(studentID, 1);
	}

	/**
	 * 根据学生id和分页信息（包括从第几条数据开始，要几条数据），得到应该返回的该学生的已经成功选出的其他学校的课的相关信息
	 * 
	 * @param studentID
	 * @param start
	 * @param numbers
	 * @return
	 */
	public static List<Map<String, Object>> getExchange(int studentID,
			int start, int numbers) {
		return getSuccessSelectedCourses(studentID, start, numbers, 1);
	}

	/**
	 * 根据该学生id和是否是本学校,得到一共选了多少门课
	 * 
	 * @param studentID
	 * @param isExchange
	 * @return
	 */
	private static long getSuccessSelectedNumberCourses(int studentID,
			int isExchange) {
		String sql = "select count(*) from main_student_course where student_id="
				+ studentID
				+ " and is_exchange="
				+ isExchange
				+ " and admin_status=1 and tutor_status=1 and lecturer_status=1";
		return baseDao.getLong(sql);
	}

	/**
	 * 根据该学生id和是否是本学校,得到一共选了多少门课
	 * 
	 * @param studentID
	 * @param isExchange
	 * @return
	 */
	private static long getSelectingNumberCourses(int studentID,
			int isExchange) {
		String sql = "select count(*) from main_student_course where student_id="
				+ studentID
				+ " and is_exchange="
				+ isExchange
				+ " and (admin_status=0 or tutor_status=0 or lecturer_status=0)";
		return baseDao.getLong(sql);
	}
	
	/**
	 * 根据该学生id和是否是本学校,得到正在选的相应的课程的信息
	 * 
	 * @param studentID
	 * @param isExchange
	 * @return
	 */
	private static List<Map<String, Object>> getSelectingCourses(int studentID,
			int start, int numbers, int isExchange) {
		// 要返回的结果列表
		List<Map<String, Object>> resultList = null;
		String headSql = "select msc.id msc_id,"
				+ "msc.admin_status msc_admin_status,msc.tutor_status msc_tutor_status,msc.lecturer_status msc_lecturer_status,"
				+ "mc.id mc_id,mc.coursename mc_name,mc.deadline mc_deadline,"
				+ "md.id md_id,md.departmentname md_name,"
				+ "mu.id mu_id,mu.universityname mu_name,"
				+ "su.id su_id,su.username su_name ";
		String middleSql = "from main_student_course msc left join main_course mc on msc.course_id=mc.id "
				+ "left join main_department md on mc.department_id=md.id "
				+ "left join main_university mu on md.university_id=mu.id "
				+ "left join sys_user su on mc.lecturer_id=su.id ";
		String endSql = "where msc.student_id="
				+ studentID
				+ " and msc.is_exchange="
				+ isExchange
				+ " and (msc.admin_status=0 or msc.tutor_status=0 or msc.lecturer_status=0)";
		String limitSql = " limit " + start + "," + numbers;
		resultList = baseDao.getList(headSql + middleSql + endSql + limitSql);
		int i = 1;
		for (Map<String, Object> course : resultList) {
			course.put("seq", i++);
			course.put("status", "Reviewing");
			java.sql.Timestamp  now = new java.sql.Timestamp ((new java.util.Date()).getTime());
			java.sql.Timestamp  deadlineOfSelecting = (java.sql.Timestamp )course.get("mc_deadline");
			if(now.before(deadlineOfSelecting)){
				course.put("deletable", true);
			}
		}
		return resultList;
	}

	/**
	 * 根据该学生id和是否是本学校,得到已选了的相应的课程的信息
	 * 
	 * @param studentID
	 * @param isExchange
	 * @return
	 */
	private static List<Map<String, Object>> getSuccessSelectedCourses(
			int studentID, int start, int numbers, int isExchange) {
		// 要返回的结果列表
		List<Map<String, Object>> resultList = null;
		String headSql = "select msc.id msc_id,msc.score msc_score,"
				+ "mc.id mc_id,mc.coursename mc_name,"
				+ "md.id md_id,md.departmentname md_name,"
				+ "mu.id mu_id,mu.universityname mu_name,"
				+ "su.id su_id,su.username su_name ";
		String middleSql = "from main_student_course msc left join main_course mc on msc.course_id=mc.id "
				+ "left join main_department md on mc.department_id=md.id "
				+ "left join main_university mu on md.university_id=mu.id "
				+ "left join sys_user su on mc.lecturer_id=su.id ";
		String endSql = "where msc.student_id="
				+ studentID
				+ " and msc.is_exchange="
				+ isExchange
				+ " and msc.admin_status=1 and msc.tutor_status=1 and msc.lecturer_status=1";
		String limitSql = " limit " + start + "," + numbers;
		resultList = baseDao.getList(headSql + middleSql + endSql + limitSql);
		int i = 1;
		for (Map<String, Object> course : resultList) {
			course.put("seq", i++);
			if (course.get("msc_score") == null) {
				course.put("msc_score", "undetermited");
			}
		}
		return resultList;
	}

}
