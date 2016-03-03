package base.zfarming.servlet.depadmin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.zfarming.dao.BaseDao;
import base.zfarming.utils.edu.ParamUtils;
import base.zfarming.utils.edu.RoleUtils;

/**
 * Servlet implementation class ExcOfficeConfirm
 */
public class ConfirmSubmit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     */
    public ConfirmSubmit() {
        super();
    }

	/**
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int stuCouId = ParamUtils.StringPauseInt(request.getParameter("stuCouId"));
		int couDepartId = ParamUtils.StringPauseInt(request.getParameter("couDepartId"));
		int adminStatus = ParamUtils.StringPauseInt(request.getParameter("status"));
		BaseDao baseDao = new BaseDao();
		String tutorId = baseDao.getObject("select id from sys_user where department_id="+couDepartId+" and role_id="+RoleUtils.DeptutorRoleID).toString();
		int row = baseDao.updateOrDelete("update main_student_course set admin_status="+adminStatus+",tutor_id="+tutorId+" where id="+stuCouId);
		if(row >= 1){
			response.getOutputStream().print(ParamUtils.SuccessJson);
		} else {
			response.getOutputStream().print(ParamUtils.ErrorJson("Error Operation"));
		}
	}

}
