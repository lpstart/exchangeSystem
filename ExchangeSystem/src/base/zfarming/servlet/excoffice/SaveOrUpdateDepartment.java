package base.zfarming.servlet.excoffice;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.zfarming.dao.BaseDao;
import base.zfarming.utils.edu.ParamUtils;
import base.zfarming.utils.edu.RoleUtils;

public class SaveOrUpdateDepartment extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8948439755767718151L;

	
	
	
	@Override
	@SuppressWarnings("unchecked")
	/**
	 * exchange新增或修改学院(main_department)跳转
	 * @Description
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = ParamUtils.pauseString(request.getParameter("action"));
		String departmentId = ParamUtils.pauseString(request.getParameter("id"));
		request.setAttribute("action", action);
		String modelFile = "/views/excoffice/saveOrUpdateDepartment.jsp";
		if("add".equals(action)){
			
		} else {
			BaseDao baseDao = new BaseDao();
			Map<String, Object> department = baseDao.get("select * from main_department where id="+departmentId);
			request.setAttribute("department", department);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(modelFile);
		dispatcher.forward(request, response);
	}
	@Override
	@SuppressWarnings("unchecked")
	/**
	 * exchange新增或修改学院(main_department)
	 * @Description
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BaseDao baseDao = new BaseDao();
		String id = ParamUtils.pauseString(request.getParameter("departmentid"));
		String action = ParamUtils.pauseString(request.getParameter("action"));
		String departmentname = ParamUtils.pauseString(request.getParameter("departmentname"));
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		if("add".equals(action)){//新增
			String insertSql = "insert into main_department(departmentname,university_id) values('"+departmentname+"','"+user.get("department_id")+"')";
			baseDao.insert(insertSql);
		} else if("edit".equals(action)){//修改
			String updateSql = "update main_department set departmentname='"+departmentname+"' where id="+id;
			baseDao.updateOrDelete(updateSql);
		}
		String listModel = "DepartmentList.htm";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listModel);
		dispatcher.forward(request, response);
		
	}
}
