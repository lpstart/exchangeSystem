package base.zfarming.servlet.students;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.zfarming.dao.BaseDao;

public class InsertSelectedCourses extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3018079472243805480L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		BaseDao baseDao = new BaseDao();
		@SuppressWarnings("unchecked")
		Map<String, Object> users = (Map<String, Object>) req.getSession()
				.getAttribute("user");
		String splitChar = ",";
		int userID = (Integer) users.get("id");

		String sqlForExofficeID = "select excoffice_id from main_university where id=(select university_id from main_department where id=(select department_id from sys_user where id ="
				+ userID + "))";
		int exoffice_id = (Integer) baseDao.getObject(sqlForExofficeID);

		@SuppressWarnings("rawtypes")
		Enumeration parameterNames = req.getParameterNames();
		ArrayList<String> courseIDs = new ArrayList<String>();
		while (parameterNames.hasMoreElements()) {
			String paramName = (String) parameterNames.nextElement();
			if (paramName.startsWith("**course")) {
				courseIDs.add(req.getParameter(paramName));
			}
		}
		if (courseIDs.size() > 0) {
			String sql = "insert into main_student_course(student_id,course_id,select_date,status,excoffice_id,is_exchange) values";
			for (String courseID : courseIDs) {
				sql = sql + "(" + userID + splitChar + courseID + splitChar
						+ "NOW()" + splitChar + 1 + splitChar+exoffice_id + splitChar + 0
						+ "),";
			}
			sql = sql.substring(0, sql.length() - 1);
			baseDao.updateOrDelete(sql);
		}
		req.getRequestDispatcher("/CourseSelectionForm.htm").forward(req, resp);
	}

}
