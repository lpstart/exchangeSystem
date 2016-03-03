package base.zfarming.servlet.students;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.zfarming.dao.BaseDao;

public class AddNewCourseOutcome extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3446163119671859434L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) req.getSession().getAttribute("user");
		int userDepartmentID = (Integer) user.get("department_id");
		String sqlUniversities = "select * from main_university where id != (select university_id from main_department where id="
				+ userDepartmentID + ")";
		BaseDao baseDao = new BaseDao();
		List<Map<String, Object>> universitiesList = baseDao.getList(sqlUniversities);
		req.setAttribute("universities", universitiesList);
		req.getRequestDispatcher("/views/students/addNewCoursesOutcome.jsp").forward(req, resp);
	}

}
