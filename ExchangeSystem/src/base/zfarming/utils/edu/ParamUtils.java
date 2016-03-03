package base.zfarming.utils.edu;

import java.text.SimpleDateFormat;

public class ParamUtils {

	
	public final static String SuccessJson = "{\"result\":1}";
	public static String ErrorJson(String errorResult){
		return "{\"result\":0,info:\""+errorResult+"\"}";
	}
	
	public static String pauseString(String param){
		return pauseString(param, "");
	}
	
	public static String pauseString(String param,String defaultStr){
		if(param == null){
			return defaultStr;
		} else {
			return param;
		}
	}
	
	public static int StringPauseInt(String param){
		return StringPauseInt(param, 0);
	}
	
	public static int StringPauseInt(String param, int defaultInt){
		try {
			return Integer.valueOf(param);
		} catch (Exception e) {
			return defaultInt;
		}
	}
	
}
