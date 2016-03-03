package base.zfarming.servlet.excoffice;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import base.zfarming.dao.BaseDao;

public class selectUser extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5017168468370984106L;

	
	
	
	@Override
	@SuppressWarnings("unchecked")
	/**
	 * 查询用户信息，用于修改departAdmin用户的回显
	 * @Description
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BaseDao baseDao = new BaseDao();
		String id=request.getParameter("departmentadmin_id");
		String sql = "select * from sys_user where id="+id ;
		Map<String,Object> user = baseDao.get(sql);
		request.setAttribute("user", user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/excoffice/editOrUpdateUser.jsp");
		dispatcher.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
