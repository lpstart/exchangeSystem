package base.zfarming.servlet.deplecturer;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.zfarming.dao.BaseDao;
import base.zfarming.utils.edu.ParamUtils;
import base.zfarming.utils.sys.DateTool;

/**
 * Servlet implementation class CourseManage
 */
@SuppressWarnings("unchecked")
public class CourseManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     */
    public CourseManage() {
        super();
    }

	/**
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = ParamUtils.pauseString(request.getParameter("action"));
		String courseId = ParamUtils.pauseString(request.getParameter("courseId"));
		request.setAttribute("action", action);
		String modelFile = "/views/deplecturer/course.jsp";
		if("add".equals(action)){
			
		} else {
			BaseDao baseDao = new BaseDao();
			Map<String, Object> course = baseDao.get("select * from main_course c join sys_user u on c.lecturer_id=u.id where c.id="+courseId);
			request.setAttribute("course", course);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(modelFile);
		dispatcher.forward(request, response);
	}

	/**
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = ParamUtils.pauseString(request.getParameter("action"));
		String courseId = ParamUtils.pauseString(request.getParameter("courseid"));
		String courseName = ParamUtils.pauseString(request.getParameter("coursename"));
		String courseInfo = ParamUtils.pauseString(request.getParameter("courseinfo"));
		String deadline = DateTool.getStringDate(ParamUtils.pauseString(request.getParameter("deadline")), "yyyy-MM-dd HH:mm:ss");
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		String userid = ParamUtils.pauseString(user.get("id")+"");
		String departmentId = ParamUtils.pauseString(user.get("department_id")+"");
		BaseDao baseDao = new BaseDao();
		if("add".equals(action)){
			baseDao.insert("insert into main_course(coursename,courseinfo,deadline,department_id,lecturer_id) values('"+courseName+"','"+courseInfo+"','"+deadline+"',"+departmentId+","+userid+")");
		} else if("edit".equals(action)){
			baseDao.updateOrDelete("update main_course set coursename='"+courseName+"',courseinfo='"+courseInfo+"',deadline='"+deadline+"' where id="+courseId);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("TeachingList.htm");
		dispatcher.forward(request, response);
	}

}
