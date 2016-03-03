package base.zfarming.servlet.students;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.zfarming.utils.edu.ParamUtils;

public class GetEntityInformation extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2274292339702039304L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String objectName =req.getParameter("objectName").replace("'", "");
		int objectID = ParamUtils.StringPauseInt(req.getParameter("objectID"));
		Map<String, Object> objectInfo = null;
		if (objectName.equalsIgnoreCase(GetEntityInfo.COURSE)) {
			objectInfo = GetEntityInfo.getCourseInfo(objectID);
			objectInfo.put("objectName", GetEntityInfo.COURSE);
		} else if (objectName.equalsIgnoreCase("department")) {
			objectInfo = GetEntityInfo.getDepartmentInfo(objectID);
			objectInfo.put("objectName", GetEntityInfo.DEPARTMENT);
		} else if (objectName.equalsIgnoreCase(GetEntityInfo.UNIVERSITY)) {
			objectInfo = GetEntityInfo.getUniversityInfo(objectID);
			objectInfo.put("objectName", GetEntityInfo.UNIVERSITY);
		} else if (objectName.equalsIgnoreCase(GetEntityInfo.USER)) {
			objectInfo = GetEntityInfo.getUserInfo(objectID);
			objectInfo.put("objectName", GetEntityInfo.USER);
		}
		req.setAttribute("objectInfo", objectInfo);
		req.getRequestDispatcher("/views/students/info.jsp").forward(req, resp);
	}
}
