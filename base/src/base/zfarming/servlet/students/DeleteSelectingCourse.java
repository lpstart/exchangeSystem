package base.zfarming.servlet.students;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.zfarming.dao.BaseDao;
import base.zfarming.utils.edu.ParamUtils;

public class DeleteSelectingCourse extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7602288588418104713L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int is_exchange = ParamUtils.StringPauseInt(req
				.getParameter("isChange"));
		int main_student_courseID = ParamUtils.StringPauseInt(req
				.getParameter("deletSelectingCourseID"));
		String deleteSql = "delete from main_student_course where id="
				+ main_student_courseID;
		BaseDao baseDao = new BaseDao();
		baseDao.updateOrDelete(deleteSql);
		if (is_exchange == 1) {
			req.getRequestDispatcher("CourseSelectionFormOutcome.htm").forward(
					req, resp);
		} else if (is_exchange == 0) {
			req.getRequestDispatcher("CourseSelectionForm.htm").forward(req,
					resp);
		} else {
			req.getRequestDispatcher("Welcome.htm").forward(req, resp);
		}
	}

}
