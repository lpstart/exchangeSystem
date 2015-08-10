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
import base.zfarming.utils.edu.ParamUtils;
import base.zfarming.utils.sys.PageUtils;

public class DepartmentList extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5017168468370984106L;

	
	
	
	@Override
	@SuppressWarnings("unchecked")
	/**
	 * exchange查看本学校的department
	 * @Description
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BaseDao baseDao = new BaseDao();
		int pageIndex = ParamUtils.StringPauseInt(request.getParameter("pageIndex"), 1);
		int pageSize = ParamUtils.StringPauseInt(request.getParameter("pageSize"), PageUtils.DefaultPageSize);
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		String baseSql="from main_department dep left join sys_user u on dep.departmentadmin_id=u.id where university_id="+user.get("department_id");
		String pageSql="select dep.*,u.username "+baseSql+" limit "+((pageIndex-1)*pageSize)+","+pageSize;
		String countSql = "select count(1) " + baseSql;
		List<Map<String,Object>> departments = baseDao.getList(pageSql);
		long count = baseDao.getLong(countSql);
		PageUtils stuCoursesPage = new PageUtils(pageSize);
		stuCoursesPage.setTotleSize(count);
		stuCoursesPage.setPageIndex(pageIndex);
		stuCoursesPage.setList(departments);
		request.setAttribute("departments", departments);
		request.setAttribute("stuCoursesPage", stuCoursesPage);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/excoffice/departmentList.jsp");
		dispatcher.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
