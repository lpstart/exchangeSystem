package base.zfarming.servlet.deplecturer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.zfarming.dao.BaseDao;

/**
 */
@SuppressWarnings("unchecked")
public class TeachingList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     */
    public TeachingList() {
        super();
    }

	/**
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		//Map<String, Object> role = (Map<String, Object>) request.getSession().getAttribute("role");
		String lecturerId = user.get("id")+"";
		BaseDao baseDao = new BaseDao();
		List<Map<String, Object>> teachList = baseDao.getList("select * from main_course where lecturer_id="+lecturerId);
		request.setAttribute("teachList", teachList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/deplecturer/teachlist.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
