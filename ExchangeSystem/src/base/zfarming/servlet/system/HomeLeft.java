package base.zfarming.servlet.system;

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
 * Servlet implementation class HomeLeft
 */
public class HomeLeft extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeLeft() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> role = (Map<String, Object>) request.getSession().getAttribute("role");
		BaseDao baseDao = new BaseDao();
		String menuSql = "select m.* from sys_role_menu rm join sys_menu m on m.id=rm.menu_id where m.parent_id=0 and m.`status`=1 and rm.`status`=1 and rm.role_id="+role.get("id")+" order by m.seq";
		List<Map<String, Object>> menus = baseDao.getList(menuSql);
		for (Map<String, Object> menu : menus) {
			String childrenMenuSql = "select m.* from sys_role_menu rm join sys_menu m on m.id=rm.menu_id where m.`status`=1 and rm.`status`=1 and m.parent_id="+menu.get("id")+" and rm.role_id="+role.get("id")+" order by m.seq";
			List<Map<String, Object>> cMenus = baseDao.getList(childrenMenuSql);
			if(cMenus != null){
				menu.put("childrenSize", cMenus.size());
				menu.put("children", cMenus);
			}
		}
		request.setAttribute("menus", menus);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/home/left.jsp");
		dispatcher .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
