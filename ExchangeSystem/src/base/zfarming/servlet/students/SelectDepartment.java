package base.zfarming.servlet.students;

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

public class SelectDepartment extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2827562649194132573L;

	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 分页信息获得
		// 当前页的页码
		int pageIndex = ParamUtils.StringPauseInt(
				request.getParameter("pageIndex"), 1);
		// 每页所包含的记录数
		int pageSize = ParamUtils.StringPauseInt(request.getParameter("pageSize"),
				PageUtils.DefaultPageSize);
		// 最终返回给用户的包含课程和分页信息的类
		PageUtils departmentPage = new PageUtils(pageSize);
		
		BaseDao baseDao = new BaseDao();
		String sql = "select md.id id,md.departmentname departmentname,mu.universityname universityname "
				+ "from main_department md left join main_university mu on md.university_id=mu.id "
				+ "limit "+((pageIndex-1)*pageSize)+","+pageSize;
		List<Map<String,Object>> departments = baseDao.getList(sql);
		String sqlTotalNumber = "select count(*) from main_department";
		long totalNumber = baseDao.getLong(sqlTotalNumber);
		
		departmentPage.setList(departments);
		departmentPage.setPageIndex(pageIndex);
		departmentPage.setPageSize(pageSize);
		departmentPage.setTotleSize(totalNumber);
		request.setAttribute("DepartmentPage", departmentPage);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/students/selectDepartment.jsp");
		dispatcher.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
