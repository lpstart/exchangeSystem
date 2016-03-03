package base.zfarming.servlet.students;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.zfarming.dao.BaseDao;

public class AddNewCourses extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5889926778683054601L;

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
		int depIDForUser = (Integer) user.get("department_id");
		String getDepartmentsSql = "select * from main_department where university_id=(select university_id from main_department where id="
				+ depIDForUser + ") ";
		BaseDao baseDao = new BaseDao();
		List<Map<String, Object>> departments = baseDao
				.getList(getDepartmentsSql);
		req.setAttribute("departments", departments);
		req.getRequestDispatcher("/views/students/addNewCourses.jsp").forward(
				req, resp);
	}

}
