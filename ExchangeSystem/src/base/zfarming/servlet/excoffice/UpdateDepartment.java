package base.zfarming.servlet.excoffice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.zfarming.dao.BaseDao;
import base.zfarming.utils.edu.ParamUtils;

/**
 * Servlet implementation class AddDepartment
 */
public class UpdateDepartment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDepartment() {
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
		int departAdminId = ParamUtils.StringPauseInt(request.getParameter("departAdminId"));
		BaseDao baseDao = new BaseDao();
		String updateSql = "update main_student_course set admin_id="+departAdminId+" where id="+stuCouId;
		int row = baseDao.updateOrDelete(updateSql);
		if(row >= 1){
			response.getOutputStream().print(ParamUtils.SuccessJson);
		} else {
			response.getOutputStream().print(ParamUtils.ErrorJson("Error Operation"));
		}
	}

}
