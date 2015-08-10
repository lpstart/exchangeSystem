package base.zfarming.utils.edu;

public class RoleUtils {
	
	public final static int AdminRoleID = 1;
	public final static int StudentRoleID = 2;
	public final static int ExcofficeRoleID = 3;
	public final static int DepadminRoleID = 4;
	public final static int DeptutorRoleID = 5;
	public final static int DeplecturerRoleID = 6;
	
	public static boolean isExcoffice(String roleIdStr){
		int roleId = ParamUtils.StringPauseInt(roleIdStr);
		return isExcoffice(roleId);
	}
	public static boolean isExcoffice(int roleId){
		if(roleId == ExcofficeRoleID){
			return true;
		}
		return false;
	}
	
	public static boolean isDepartAdmin(String roleIdStr){
		int roleId = ParamUtils.StringPauseInt(roleIdStr);
		return isDepartAdmin(roleId);
	}
	public static boolean isDepartAdmin(int roleId){
		if(roleId == DepadminRoleID){
			return true;
		}
		return false;
	}
	
	public static boolean isDepartTutor(String roleIdStr){
		int roleId = ParamUtils.StringPauseInt(roleIdStr);
		return isDepartTutor(roleId);
	}
	public static boolean isDepartTutor(int roleId){
		if(roleId == DeptutorRoleID){
			return true;
		}
		return false;
	}
	
	public static boolean isDepartLecturer(String roleIdStr){
		int roleId = ParamUtils.StringPauseInt(roleIdStr);
		return isDepartLecturer(roleId);
	}
	public static boolean isDepartLecturer(int roleId){
		if(roleId == DeplecturerRoleID){
			return true;
		}
		return false;
	}
	
	
	
	public static String jspRolePath(String roleStr){
		int roleId = ParamUtils.StringPauseInt(roleStr);
		return jspRolePath(roleId);
	}
	
	public static String jspRolePath(int roleId){
		if(roleId == StudentRoleID){
			return "students";
		} else if(roleId == ExcofficeRoleID){
			return "excoffice";
		} else if(roleId == DepadminRoleID){
			return "depadmin";
		} else if(roleId == DeptutorRoleID){
			return "deptutor";
		} else if(roleId == DeplecturerRoleID){
			return "deplecturer";
		} else {
			return "home";
		}
	}
	
	public static int GetCanCreateRoleId(int roleId){
		if(roleId == ExcofficeRoleID){
			return DepadminRoleID;
		} else if(roleId == DepadminRoleID){
			return DeptutorRoleID;
		} else if(roleId == DeptutorRoleID){
			return DeplecturerRoleID;
		} else {
			return StudentRoleID;
		}
	}
}
