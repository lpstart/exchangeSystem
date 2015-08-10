package base.zfarming.servlet.system;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.zfarming.dao.BaseDao;

/**
 * Servlet implementation class SignUp
 */
@SuppressWarnings("unchecked")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUp() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		String modelFile = "";
		// 如果session中有用户信息，返回主页
		if (user == null) {
			modelFile = "/sign-up.jsp";
		} else {
			modelFile = "/index.jsp";
		}
		// 如果session中没用用户信息，返回登录页面
		RequestDispatcher dispatcher = request.getRequestDispatcher(modelFile);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 得到新用户的各项数据
		// ：用户名，密码，first_name,last_name,birthday，gender，nationality，homeuniversity，email
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String first_name = request.getParameter("frist_name");
		String last_name = request.getParameter("last_name");
		String gender = request.getParameter("gender");
		String nationality = request.getParameter("nationality");
		String homeUniversity = request.getParameter("home_university");
		String email = request.getParameter("email");
		String quatationChar = "\'";
		String splitChar = ",";

		Date birthday = null;
		String sql = "";
		try {
			String birthdayTemp = request.getParameter("birthday");
			if (birthdayTemp.length() > 0) {
				birthday = new Date((new SimpleDateFormat("yyyy-MM-dd")).parse(birthdayTemp).getTime());
				// 生成插入用户的sql语句
				sql = "insert into sys_user(username,password,sex,birthday,email,first_name,last_name,nationality,homeuniversity,role_id) values("
						+ quatationChar + username + quatationChar + splitChar + quatationChar + password
						+ quatationChar + splitChar + quatationChar + gender + quatationChar + splitChar + quatationChar
						+ birthday + quatationChar + splitChar + quatationChar + email + quatationChar + splitChar
						+ quatationChar + first_name + quatationChar + splitChar + quatationChar + last_name
						+ quatationChar + splitChar + quatationChar + nationality + quatationChar + splitChar
						+ quatationChar + homeUniversity + quatationChar + splitChar + 2 + ")";
			} else {
				// 生成插入用户的sql语句,没有birthday列的
				sql = "insert into sys_user(username,password,sex,email,first_name,last_name,nationality,homeuniversity,role_id) values("
						+ quatationChar + username + quatationChar + splitChar + quatationChar + password
						+ quatationChar + splitChar + quatationChar + gender + quatationChar + splitChar + quatationChar
						+ email + quatationChar + splitChar + quatationChar + first_name + quatationChar + splitChar
						+ quatationChar + last_name + quatationChar + splitChar + quatationChar + nationality
						+ quatationChar + splitChar + quatationChar + homeUniversity + quatationChar + splitChar + 2
						+ ")";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		BaseDao baseDao = new BaseDao();
		long insertid = baseDao.insert(sql);
		StringBuilder resultJson = new StringBuilder("[");
		if (insertid != 0) {
			String sqlUser = "select * from sys_user where email=" + quatationChar + email + quatationChar;
			Map<String, Object> newUser = baseDao.get(sqlUser);
			// 如果插入成功
			//生成一个包含用户相关信息的json包返回给用户
			String genderForNewUser = (Boolean) newUser.get("sex") ? "woman" : "man";
			resultJson.append("{\"registerResult\": \"successRegister\"},");
			resultJson.append("{\"student_number\":\"" + newUser.get("id") + "\"},");
			resultJson.append("{\"email\":\"" + newUser.get("email") + "\"},");
			resultJson.append("{\"password\":\"" + newUser.get("password") + "\"},");
			resultJson.append("{\"gender\":\"" + genderForNewUser + "\"},");
			resultJson.append("{\"username\":\"" + newUser.get("username") + "\"},");
			resultJson.append("{\"first_name\":\"" + newUser.get("first_name") + "\"},");
			resultJson.append("{\"last_name\":\"" + newUser.get("last_name") + "\"},");
			resultJson.append("{\"birthday\":\"" + newUser.get("birthday") + "\"},");
			resultJson.append("{\"nationality\":\"" + newUser.get("nationality") + "\"},");
			resultJson.append("{\"homeuniversity\":\"" + newUser.get("homeuniversity") + "\"},");
			resultJson = resultJson.deleteCharAt(resultJson.length() - 1);
			resultJson.append("]");
		} else {
			//如果插入失败
			resultJson = new StringBuilder("[{\"registerResult\": \" registerFailed\"}");

			//如果是因为该email已经被注册而注册失败的，应该讲改信息告诉用户
			String sqlUser = "select * from sys_user where email=" + quatationChar + email + quatationChar;
			List<Map<String, Object>> objList = baseDao.getList(sqlUser);
			if (objList != null && objList.size() == 0) {
			} else {
				resultJson.append(",{\"msg\":\"This email is exist!\"}");
			}
			
			resultJson.append("]");
		}
		//将json包返回给用户
		response.getWriter().write(resultJson.toString());
	}
}
