package base.zfarming.servlet.deplecturer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.zfarming.dao.BaseDao;
import base.zfarming.utils.edu.ParamUtils;

/**
 * Servlet implementation class ConfirmSubmit
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
		int lecturerStatus = ParamUtils.StringPauseInt(request.getParameter("status"));
		BaseDao baseDao = new BaseDao();
		int row = baseDao.updateOrDelete("update main_student_course set lecturer_status="+lecturerStatus+" where id="+stuCouId);
		if(row >= 1){
			response.getOutputStream().print(ParamUtils.SuccessJson);
		} else {
			response.getOutputStream().print(ParamUtils.ErrorJson("Error Operation"));
		}
	}

}
