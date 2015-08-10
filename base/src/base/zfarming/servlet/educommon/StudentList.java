package base.zfarming.servlet.educommon;

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
import base.zfarming.utils.sys.PageUtils;

/**
 * Servlet implementation class StudentList
 */
@SuppressWarnings("unchecked")
public class StudentList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentList() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int isExchange = ParamUtils.StringPauseInt(request.getParameter("is_exchange"));
		int pageIndex = ParamUtils.StringPauseInt(request.getParameter("pageIndex"), 1);
		int pageSize = ParamUtils.StringPauseInt(request.getParameter("pageSize"), PageUtils.DefaultPageSize);
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		Map<String, Object> role = (Map<String, Object>) request.getSession().getAttribute("role");
		BaseDao baseDao = new BaseDao();
		String userId = user.get("id")+"";
		String baseSql = " from main_student_course sc LEFT JOIN main_course c on c.id=sc.course_id LEFT JOIN sys_user u on u.id = sc.student_id where sc.is_exchange="+isExchange,pageSql = "",countSql="";
		if(RoleUtils.isExcoffice(role.get("id")+"")){
			baseSql += " and sc.excoffice_id="+userId;
		} else if(RoleUtils.isDepartAdmin(role.get("id")+"")){
			baseSql += " and sc.admin_id="+userId;
		} else if(RoleUtils.isDepartTutor(role.get("id")+"")){
			baseSql += " and sc.tutor_id="+userId;
		} else if(RoleUtils.isDepartLecturer(role.get("id")+"")){
			baseSql += " and sc.lecturer_id="+userId;
		}
		pageSql = "select sc.*,c.*,u.*,u.department_id as stu_depart_id,c.lecturer_id as course_lecturer_id " + baseSql +" limit "+((pageIndex-1)*pageSize)+","+pageSize;
		countSql = "select count(*) " + baseSql;
		List<Map<String, Object>> list = baseDao.getList(pageSql);
		long count = baseDao.getLong(countSql);
		PageUtils stuCoursesPage = new PageUtils(pageSize);
		stuCoursesPage.setTotleSize(count);
		stuCoursesPage.setPageIndex(pageIndex);
		stuCoursesPage.setList(list);
		String modelFile = "/views/"+RoleUtils.jspRolePath(role.get("id")+"")+"/stulist.jsp";
		if(RoleUtils.isExcoffice(role.get("id")+"")) {
			List<Map<String, Object>> selectList = baseDao.getList("select * from main_department where  university_id="+user.get("department_id"));
			request.setAttribute("selectList", selectList);
		}
		request.setAttribute("stuCoursesPage", stuCoursesPage);
		RequestDispatcher dispatcher = request.getRequestDispatcher(modelFile);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
