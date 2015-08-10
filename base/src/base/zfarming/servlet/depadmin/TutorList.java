package base.zfarming.servlet.depadmin;

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
 */
@SuppressWarnings("unchecked")
public class TutorList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     */
    public TutorList() {
        super();
    }

	/**
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		String departmentId = ParamUtils.pauseString(user.get("department_id")+"");
		BaseDao baseDao = new BaseDao();
		List<Map<String, Object>> tutorList = baseDao.getList("select * from sys_user where department_id="+departmentId+" and role_id="+RoleUtils.DeptutorRoleID);
		request.setAttribute("tutorList", tutorList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/depadmin/tutorlist.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
