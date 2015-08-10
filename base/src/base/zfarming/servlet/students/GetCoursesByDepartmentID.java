package base.zfarming.servlet.students;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.zfarming.dao.BaseDao;

public class GetCoursesByDepartmentID extends HttpServlet {

	private static final long serialVersionUID = 397035277286449553L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) req.getSession()
				.getAttribute("user");
		int userID = (Integer) user.get("id");
		int departmentID = Integer.parseInt(req.getParameter("departmentID"));
		String getCourseInfo = "select * from main_course where department_id="
				+ departmentID
				+ " and deadline>now() and id not in(select course_id from main_student_course where student_id="
				+ userID + ")";
		BaseDao baseDao = new BaseDao();
		List<Map<String, Object>> coursesInfo = baseDao.getList(getCourseInfo);
		req.setAttribute("coursesInfo", coursesInfo);
		String json = "[";
		if (coursesInfo.size() != 0) {
			for (Map<String, Object> course : coursesInfo) {
				json = json + "{\"id\":" + course.get("id") + ",";
				json = json + "\"coursename\":\"" + course.get("coursename")
						+ "\",";
				json = json + "\"department_id\":"
						+ course.get("department_id") + ",";
				json = json + "\"lecturer_id\":" + course.get("lecturer_id")
						+ ",";
				json = json + "\"deadline\":\"" + course.get("deadline")
						+ "\"},";
			}
			json = json.substring(0, json.length() - 1) + "]";
		} else {
			json = json + "]";
		}
		resp.getWriter().write(json);
	}

}
