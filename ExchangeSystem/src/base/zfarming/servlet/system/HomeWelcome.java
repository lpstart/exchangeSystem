package base.zfarming.servlet.system;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeWelcome
 */
public class HomeWelcome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeWelcome() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		if((Integer)user.get("role_id")==2&&user.get("department_id")==null){
			//说明这是一个学生，并且他的学院值为空
			RequestDispatcher dispatcher = request.getRequestDispatcher("/SelectDepartment.htm");
			dispatcher.forward(request, response);
		}
//		BaseDao baseDao = new BaseDao();
//		String menuSql = "select m.* from sys_role_menu rm join sys_menu m on m.id=rm.menu_id where m.parent_id=0 and m.`status`=1 and rm.`status`=1 and rm.role_id="
//				+ role.get("id") + " order by m.seq";
		else{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/home/welcome.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
