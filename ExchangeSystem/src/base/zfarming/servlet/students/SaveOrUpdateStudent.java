package base.zfarming.servlet.students;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import base.zfarming.dao.BaseDao;

public class SaveOrUpdateStudent extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8948439755767718151L;

	/**
	 * 
	 * @Description
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 更新用户信息
	 * 
	 * @Description
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取表单数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String first_name = request.getParameter("frist_name");
		String last_name = request.getParameter("last_name");
		String sex = request.getParameter("gender");
		String nationality = request.getParameter("nationality");
		String homeuniversity = request.getParameter("home_university");
		String email = request.getParameter("email");
		BaseDao baseDao = new BaseDao();
		String quatationChar = "\'";
		String splitChar = ",";
		String sql = "update sys_user set username=" + quatationChar + username
				+ quatationChar + splitChar + "password=" + quatationChar
				+ password + quatationChar + splitChar + "sex=" + quatationChar
				+ sex + quatationChar + splitChar + "first_name="
				+ quatationChar + first_name + quatationChar + splitChar
				+ "last_name=" + quatationChar + last_name + quatationChar
				+ splitChar + "nationality=" + quatationChar + nationality
				+ quatationChar + splitChar + "homeuniversity=" + quatationChar
				+ homeuniversity + quatationChar + " where email="
				+ quatationChar + email + quatationChar;
		baseDao.updateOrDelete(sql);
		request.setAttribute("SubmitResult", "changeSuccess!");
		request.getRequestDispatcher("/EditProfile.htm").forward(request, response);
	}
}
