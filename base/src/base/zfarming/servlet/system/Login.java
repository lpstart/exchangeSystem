package base.zfarming.servlet.system;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.zfarming.dao.BaseDao;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		String modelFile = "";
		//如果session中有用户信息，返回主页
		if(user == null){
			modelFile = "/login.jsp";
		} else {
			modelFile = "/index.jsp";
		}
		//如果session中没用用户信息，返回登录页面
		RequestDispatcher dispatcher = request.getRequestDispatcher(modelFile);
		dispatcher .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String loginSql = "select * from sys_user where email='"+email+"' and password='"+password+"'";
		BaseDao baseDao = new BaseDao();
		Map<String, Object> user = baseDao.get(loginSql);
		//接受表单中的用户名密码，验证是否正确；正确返回主页，不正确返回登录页面并提示用户错误信息
		String modelFile = "";
		if(user.get("username") == null){
			modelFile = "/login.jsp";
			request.setAttribute("msg", "incorrect email or password!");
		} else {
			Map<String, Object> role = baseDao.get("select * from sys_role where id="+user.get("role_id"));
			//将用户的相关信息放入session中
			request.getSession().setAttribute("user", user);
			if(role.get("rolename")!=null){
				//将用户的角色信息放入session中
				request.getSession().setAttribute("role", role);
			}
			modelFile = "/index.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(modelFile);
		dispatcher .forward(request, response);
	}

}
