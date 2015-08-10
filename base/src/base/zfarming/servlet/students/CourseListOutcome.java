package base.zfarming.servlet.students;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import base.zfarming.utils.edu.ParamUtils;
import base.zfarming.utils.sys.PageUtils;

public class CourseListOutcome extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1840039218593666199L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 分页信息获得
		// 当前页的页码
		int pageIndex = ParamUtils.StringPauseInt(
				req.getParameter("pageIndex"), 1);
		// 每页所包含的记录数
		int pageSize = ParamUtils.StringPauseInt(req.getParameter("pageSize"),
				PageUtils.DefaultPageSize);
		// 最终返回给用户的包含课程和分页信息的类
		PageUtils stuCoursesPage = new PageUtils(pageSize);

		// 得到session里存储的当前用户的信息
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) req.getSession()
				.getAttribute("user");
		// 得到用户的id
		int studentID = (Integer) user.get("id");
		// 根据分页信息和用户id查找此次应该返回给用户的课程id和相应课程的成绩

		List<Map<String, Object>> CoursesList = GetCourse.getExchange(
				studentID, (pageIndex - 1) * pageSize, pageSize);
		// 设置返回给用户的课程列表
		stuCoursesPage.setList(CoursesList);
		// 设置返回给用户的当前页码
		stuCoursesPage.setPageIndex(pageIndex);
		// 设置返回给用的的页的大小
		stuCoursesPage.setPageSize(pageSize);
		// 设置返回给用户的总页数
		stuCoursesPage.setTotleSize(GetCourse
				.getTotalNumberCoursesExchange(studentID));

		req.setAttribute("stuCoursesPage", stuCoursesPage);
		req.getRequestDispatcher("/views/students/courseListOutcome.jsp").forward(req, resp);
	}

}
