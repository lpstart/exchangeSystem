package base.zfarming.servlet.students;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.zfarming.dao.BaseDao;

public class SelectedDepartment extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5899382871198477138L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BaseDao baseDao = new BaseDao();
		int departmentID = Integer.parseInt(request.getParameter("departmentID"));
		@SuppressWarnings("unchecked")
		Map<String,Object> user = (Map<String,Object>)request.getSession().getAttribute("user");
		int userID = (Integer)user.get("id"); 
		user.put("department_id", departmentID);
		request.getSession().setAttribute("user", user);
		String SQLForUpdateStudentDepartmentID="update sys_user set department_id="+departmentID+" where id="+userID;
		int affectRows = baseDao.updateOrDelete(SQLForUpdateStudentDepartmentID);
		request.getRequestDispatcher("/views/students/welcome.jsp").forward(request, response);
	}
}
