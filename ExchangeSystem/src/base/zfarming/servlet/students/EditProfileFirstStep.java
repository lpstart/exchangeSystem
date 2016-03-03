package base.zfarming.servlet.students;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.zfarming.dao.BaseDao;

public class EditProfileFirstStep extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5852811350155026659L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) req.getSession().getAttribute("user");
		int userID = (Integer) user.get("id");
		String sql = "select * from sys_user where id="+userID;
		Map<String,Object> userInfo = (new BaseDao()).get(sql);
		req.setAttribute("userInfo", userInfo);
		req.getRequestDispatcher("/views/students/editProfile.jsp").forward(req, resp);
	}

}
