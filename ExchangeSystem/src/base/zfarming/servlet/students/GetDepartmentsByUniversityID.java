package base.zfarming.servlet.students;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.zfarming.dao.BaseDao;

public class GetDepartmentsByUniversityID extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2814402927812094969L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int universityID = Integer.parseInt(req.getParameter("universityID"));
		String sqlDepartments = "select * from main_department where university_id=" + universityID;
		List<Map<String, Object>> departments = (new BaseDao()).getList(sqlDepartments);
		req.setAttribute("departments", departments);
		String json = "[";
		if (departments.size() != 0) {
			for (Map<String, Object> dep : departments) {
				json = json + "{\"id\":" + dep.get("id") + ",";
				json = json + "\"departmentname\":\"" + dep.get("departmentname")
						+ "\"},";
			}
			json = json.substring(0, json.length() - 1) + "]";
		} else {
			json = json + "]";
		}
		resp.getWriter().write(json);
	}

}
