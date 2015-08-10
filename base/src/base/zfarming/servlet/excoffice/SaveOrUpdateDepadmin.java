package base.zfarming.servlet.excoffice;

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

public class SaveOrUpdateDepadmin extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8948439755767718151L;

	
	
	
	@Override
	@SuppressWarnings("unchecked")
	/**
	 * exchange新增或修改departmentAdmin用户 跳转
	 * @Description
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String departmentId= ParamUtils.pauseString(request.getParameter("departmentId"));
		String departmentadmin_id = ParamUtils.pauseString(request.getParameter("departmentadmin_id"));
		String action="edit";
		if("".equals(departmentadmin_id)){
			action="add";
		}
		request.setAttribute("action", action);
		request.setAttribute("departmentId", departmentId);
		request.setAttribute("departmentadmin_id", departmentadmin_id);
		String modelFile = "/views/excoffice/setDepartmentAdmin.jsp";
		if("add".equals(action)){
			
		} else {
			BaseDao baseDao = new BaseDao();
			Map<String, Object> muser = baseDao.get("select * from sys_user where id="+departmentadmin_id);
			request.setAttribute("muser", muser);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(modelFile);
		dispatcher.forward(request, response);
	}
	@Override
	@SuppressWarnings("unchecked")
	/**
	 * exchange新增或修改departmentAdmin用户 
	 * @Description
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取表单数据
		String username = ParamUtils.pauseString(request.getParameter("username"));
		String password = ParamUtils.pauseString(request.getParameter("password"));
		String sex = ParamUtils.pauseString(request.getParameter("gender"));
		String email = ParamUtils.pauseString(request.getParameter("email"));
		String first_name = ParamUtils.pauseString(request.getParameter("first_name"));
		String last_name = ParamUtils.pauseString(request.getParameter("last_name"));
		String departmentId=ParamUtils.pauseString(request.getParameter("departmentId"));
		String action = ParamUtils.pauseString(request.getParameter("action"));
		String userId = ParamUtils.pauseString(request.getParameter("userId"));
		BaseDao baseDao = new BaseDao();
		String quatationChar = "\'";
		String result="success";
		String splitChar = ",";
		if("add".equals(action)){//新增
			String sqlForCheckEmail = "select * from sys_user where email="+quatationChar+email+quatationChar;
			List<Map<String, Object>> objList = baseDao.getList(sqlForCheckEmail);
			if(objList!=null&&objList.size()==0){//邮箱不存在
				String sql = "insert into sys_user(username,password,sex,email,first_name,last_name,role_id) values("
						+ quatationChar + username + quatationChar + splitChar + quatationChar + password + quatationChar
						+ splitChar + quatationChar + sex + quatationChar + splitChar + quatationChar + email + quatationChar
						+ splitChar + quatationChar + first_name + quatationChar + splitChar + quatationChar + last_name
						+ quatationChar + splitChar + RoleUtils.DepadminRoleID + ")";
				Long departmentadmin_id=baseDao.insert(sql);
				//更新 main_department表 departmentadmin_id字段，设置用户
				String updateSql = "update main_department set departmentadmin_id='"+departmentadmin_id+"' where id="+departmentId;
				baseDao.updateOrDelete(updateSql);
			}else{//邮箱存在
				result="error";
			}
			
		} else if("edit".equals(action)){//修改
			String sql= "update sys_user set username="+quatationChar+username+quatationChar+splitChar+
					"password="+quatationChar+password+quatationChar+splitChar+
					"sex="+quatationChar+sex+quatationChar+splitChar+
					"email="+quatationChar+email+quatationChar+splitChar+
					"first_name="+quatationChar+first_name+quatationChar+splitChar+
					"last_name="+quatationChar+last_name+quatationChar+splitChar+
					"role_id="+quatationChar+RoleUtils.DepadminRoleID+quatationChar+
					" where id="+quatationChar+userId+quatationChar;
			baseDao.updateOrDelete(sql);
		}
		/*String listModel = "DepartmentList.htm";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listModel);
		dispatcher.forward(request, response);*/
		response.getWriter().print("{\"success\":\"" + result + "\"}");
	
	}
}
