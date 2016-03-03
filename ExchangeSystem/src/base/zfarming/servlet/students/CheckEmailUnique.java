package base.zfarming.servlet.students;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.zfarming.dao.BaseDao;

public class CheckEmailUnique extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8279464929844157515L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String quatationChar = "\'";
		String email = (String)req.getParameter("email");
		BaseDao baseDao = new BaseDao();
		String sqlForCheckEmail = "select * from sys_user where email="+quatationChar+email+quatationChar;
		List<Map<String, Object>> objList = baseDao.getList(sqlForCheckEmail);
		String result="success";
		
		if(objList!=null&&objList.size()==0){
			
		}else{
			result="error";
		}
		resp.getWriter().print("{\"success\":\"" + result + "\"}");
	}
}
