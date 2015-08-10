package base.zfarming.servlet.excoffice;

import java.io.IOException;
import java.util.Map;

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
		String isExchange = ParamUtils.pauseString(request.getParameter("isExchange"));
		int couDepartId = ParamUtils.StringPauseInt(request.getParameter("couDepartId"));
		int stuDepartId = ParamUtils.StringPauseInt(request.getParameter("stuDepartId"));
		int excStatus = ParamUtils.StringPauseInt(request.getParameter("status"));
		BaseDao baseDao = new BaseDao();
		if("false".equals(isExchange) && couDepartId != stuDepartId){
			//跨校选课处理
			Map<String, Object> excofficeUser = baseDao.get("select * from sys_user where role_id="+RoleUtils.ExcofficeRoleID+" and department_id="+couDepartId);
			int excOfficeId = ParamUtils.StringPauseInt(excofficeUser.get("id")+"");
			if(excOfficeId != 0 && excStatus == 1){
				String insertSql = "insert into main_student_course(student_id,course_id,select_date,status,excoffice_id,is_exchange) values("+stuDepartId+","+couDepartId+",NOW(),0,"+excOfficeId+",1)";
				baseDao.insert(insertSql);
			}
		}
		int row = baseDao.updateOrDelete("update main_student_course set status="+excStatus+" where id="+stuCouId);
		if(row >= 1){
			response.getOutputStream().print(ParamUtils.SuccessJson);
		} else {
			response.getOutputStream().print(ParamUtils.ErrorJson("Error Operation"));
		}
	}

}
