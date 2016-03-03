package base.zfarming.servlet.educommon;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.zfarming.dao.BaseDao;
import base.zfarming.utils.edu.ParamUtils;
import base.zfarming.utils.edu.RoleUtils;
import base.zfarming.utils.sys.DateTool;

/**
 */
@SuppressWarnings("unchecked")
public class UserManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     */
    public UserManage() {
        super();
    }

	/**
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = ParamUtils.pauseString(request.getParameter("action"));
		String userId = ParamUtils.pauseString(request.getParameter("userId"));
		request.setAttribute("action", action);
		String modelFile = "/views/educommon/user.jsp";
		if("add".equals(action)){
			
		} else {
			BaseDao baseDao = new BaseDao();
			Map<String, Object> mUser = baseDao.get("select * from sys_user u join main_department d on d.id=u.department_id join main_university un on un.id=d.university_id where u.id="+userId);
			request.setAttribute("muser", mUser);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(modelFile);
		dispatcher.forward(request, response);
	}

	/**
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = ParamUtils.pauseString(request.getParameter("action"));
		String userId = ParamUtils.pauseString(request.getParameter("userId"));
		
		String username = ParamUtils.pauseString(request.getParameter("username"));
		String password = ParamUtils.pauseString(request.getParameter("password"));
		String first_name = ParamUtils.pauseString(request.getParameter("frist_name"));
		String last_name = ParamUtils.pauseString(request.getParameter("last_name"));
		int gender = ParamUtils.StringPauseInt(request.getParameter("gender"));
		String nationality = ParamUtils.pauseString(request.getParameter("nationality"));
		String email = ParamUtils.pauseString(request.getParameter("email"));
		String birthday = DateTool.getStringDate(ParamUtils.pauseString(request.getParameter("birthday"),"1990-01-01"), "yyyy-MM-dd");
		
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		int roleId = ParamUtils.StringPauseInt(user.get("role_id")+"");
		String departmentId = ParamUtils.pauseString(user.get("department_id")+"");
		int createRoleId = RoleUtils.GetCanCreateRoleId(roleId);
		String quatationChar = "\'";
		String splitChar = ",";
		
		BaseDao baseDao = new BaseDao();
		if("add".equals(action)){
			String inserSql = "insert into sys_user(username,password,sex,birthday,email,first_name,last_name,nationality,department_id,role_id) values("
					+ quatationChar + username + quatationChar + splitChar + quatationChar + password
					+ quatationChar + splitChar + quatationChar + gender + quatationChar + splitChar + quatationChar
					+ birthday + quatationChar + splitChar + quatationChar + email + quatationChar + splitChar
					+ quatationChar + first_name + quatationChar + splitChar + quatationChar + last_name
					+ quatationChar + splitChar + quatationChar + nationality + quatationChar + splitChar
					+ departmentId + splitChar + createRoleId + ")";
			baseDao.insert(inserSql);
		} else if("edit".equals(action)){
			String updateSql = "update sys_user set username='"+username+"',password='"+password+"',sex="+gender+",birthday='"+birthday+"',email='"+email+"',first_name='"+first_name+"',last_name='"+last_name+"',nationality='"+nationality+"' where id="+userId;
			baseDao.updateOrDelete(updateSql);
		}
		String listModel = "";
		if(createRoleId == RoleUtils.DeplecturerRoleID){
			listModel = "LecturerList.htm";
		} else if(createRoleId == RoleUtils.DeptutorRoleID){
			listModel = "TutorList.htm";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(listModel);
		dispatcher.forward(request, response);
	}

}
