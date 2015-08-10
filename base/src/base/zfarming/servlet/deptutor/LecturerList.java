package base.zfarming.servlet.deptutor;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.zfarming.dao.BaseDao;
import base.zfarming.utils.edu.ParamUtils;
import base.zfarming.utils.edu.RoleUtils;

/**
 * Servlet implementation class LecturerList
 */
@SuppressWarnings("unchecked")
public class LecturerList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     */
    public LecturerList() {
        super();
    }

	/**
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		String departId = ParamUtils.pauseString(user.get("department_id")+"");
		BaseDao baseDao = new BaseDao();
		List<Map<String, Object>> lectList = baseDao.getList("select * from sys_user where department_id="+departId+" and role_id="+RoleUtils.DeplecturerRoleID);
		request.setAttribute("lectList", lectList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/deptutor/lectlist.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
