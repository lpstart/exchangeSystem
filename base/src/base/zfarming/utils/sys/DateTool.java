package base.zfarming.utils.sys;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>日期工具类</p>
 *
 */
public class DateTool {
private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String getNowDate(){
		return sf.format(new Date());
	}
	
	public static String parseDate( Date date ){
		return sf.format(date);
	}
	
	public static String getWeekName( String str , String fmt){
		SimpleDateFormat sfm = new SimpleDateFormat(fmt);
		Date date = null;
		try {
			date = sfm.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String result = null;
		switch( calendar.get(Calendar.DAY_OF_WEEK) - 1 ){
			case 0:{
				result = "星期日";
				break;
			}
			case 1:{
				result = "星期一";
				break;
			}
			case 2:{
				result = "星期二";
				break;
			}			
			case 3:{
				result = "星期三";
				break;
			}			
			case 4:{
				result = "星期四";
				break;
			}			
			case 5:{
				result = "星期五";
				break;
			}			
			case 6:{
				result = "星期六";
				break;
			}
		}
		return result;
	}
	
	
	public static String getStringDate( String str , String fmt){
		SimpleDateFormat sfm = new SimpleDateFormat(fmt);
		String result = null;
		try {
			if( str != null && !"".equals(str) ){
				result = sf.format(sfm.parse(str));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String getWeek( Integer week ){
		String result = null;

		return result;
	}
	/**
     * 获得当前月份
     * @return 当前月份
     */
    public static int getNowYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
    
	/**
     * 获得当前月份
     * @return 当前月份
     */
    public static int getNowMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }
    
	/**
     * 按指定格式取得当前系统日期
     * @return 当前系统日期
     * 如fmt="yyyy年MM月dd",返回2007年11月20日
     * 如fmt="hh:mm:ss",返回13:40:20
     *  Y/y-年；M-月；D/d日期；H-小时（24小时）;h-小时（12小时），m-分钟，S/s-秒
     */
    public static String getNowDateByFmt(String fmt) {
        Date now = new Date();
        fmt = fmt.replace('Y','y');
        fmt = fmt.replace('D','d');
        fmt = fmt.replace('S','s');
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        String nowDate = sdf.format(now);
        return nowDate;
    }
    
	/**
	 * <p>获得系统当前时间，格式为：yyyyMMddHHmmss</p>
	 * @return
	 */
	public static String getNowDateTime() {
		return formatDate("yyyyMMddHHmmss", new Date());
	}
    
	/**
	 * <p>获得系统当前时间，格式为：yyyy-MM-dd</p>
	 * @return
	 */
	public static String getCurrYMD() {
		return formatDate("yyyy-MM-dd", new Date());
	}
    
	/**
	 * <p>获得系统当前时间，格式为：yyyyMMdd</p>
	 * @return 
	 */
	public static String getY2M2D() {
		return formatDate("yyyyMMdd", new Date());
	}
	
	/**
    * 获得指定日期的前n分钟/后n分钟
    * 
    * @param int dayNum (小于0为前n分钟/大于0为后n几分钟)
    * @return 日期：格式为yyyyMMddHHmmss
    */
   public static String getDiffMinute(int minuteNum) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + minuteNum);
		return formatDate("yyyyMMddHHmmss", cal.getTime());
    }
	
	/**
	 * </p>取得系统一分钟之前的时间，格式为：yyyyMMddHHmmss</p>
	 * @return 日期：格式为yyyyMMddHHmmss
	 */
	public static String getOneMinuteBefore() {
		return getDiffMinute(1);
	}
	
	/**
	 * <p>格式化日期</p>
	 * @param format 日期格式，如:"yyyyMMdd"
	 * @param date 日期
	 * @return 返回格式化后的日子字符串
	 * @throws Exception
	 */
	public static String formatDate(String format, Date date) {
		DateFormat fmt = new SimpleDateFormat(format);
		return fmt.format(date);
	}
	
	/** 
	 * 获得指定日期的前n分/后n分
	 * @param time 时分秒 yyyy-MM-dd HH:mm:ss
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getDiffMinute(int minute, String date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDate(date, "yyyy-MM-dd HH:mm:ss"));
	    calendar.add(Calendar.MINUTE, minute);    
		return DateTool.formatDate("yyyy-MM-dd HH:mm:ss",calendar.getTime());      
	}  
	
	/**
	 * <p>日期格式转换</p>
	 * @param toFormat 目标格式
	 * @param sourceFormat 源格式
	 * @param source 源日期
	 * @return 目标日期
	 * @throws ParseException 当格式转换出错时抛ParseException异常
	 */
	public static String exchange( String toFormat, String sourceFormat, String source ){
		DateFormat toFmt = new SimpleDateFormat(toFormat);
		DateFormat sourceFmt = new SimpleDateFormat(sourceFormat);
		Date sourceDate;
		try {
			sourceDate = sourceFmt.parse(source);
			return toFmt.format(sourceDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * <p>日期格式转换页面用</p>
	 * @param toFormat 目标格式
	 * @param sourceFormat 源格式
	 * @param source 源日期
	 * @return 目标日期
	 * @throws ParseException 当格式转换出错时抛ParseException异常
	 */
	public static String exchangepage(String toFormat, String sourceFormat, String source){
		if(source==null||"".equals(source)) return "--";
		return exchange(toFormat, sourceFormat, source );
	}
	
	/**
	 * <p>平年闰年判断</p>
	 * <p>
	 * 	①能被4整除，但不能被100整除的年份都是闰年；
	 * 	②能被100整除，同时又能被400整除的年份也是闰年。不符合这两个条件的年份就不是闰年。
	 * </p>
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear( int year ) {
		
		if ( year % 4 == 0 && year % 100 != 0 )
			return true;
		
		if ( year %100 == 0 && year % 400 == 0 )
			return true;
		
		return false;
	}
	
	 /**
	    * 把字符串转成日期对象
	    * @param strTime 时间字符串
	    * @param timeFmt 时间格式
	    * @return
	    */
	public static Date getDate(String strTime,String timeFmt) {
		
		DateFormat fmt = new SimpleDateFormat(timeFmt);
		Date date = null;
		try {
			date = fmt.parse(strTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date getNextDate( Date date ) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR)+1);
		return c.getTime();
	}
	public static Date getBeforDate( Date date ) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR)-1);
		return c.getTime();
	}
	 /**
     * 获得指定日期的前一天
     * 
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getDayBefore(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }
    
    /**
     * 获得当前日期的前n天/后n天
     * @param Date date
     * @param int dayNum (小于0为前n天/大于0为后n天)
     * @return
     */
    public static String getDiffDay(int dayNum) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(new Date());
    	cal.add(Calendar.DAY_OF_YEAR, dayNum);
        return formatDate("yyyy-MM-dd", cal.getTime());
    }
    
    /**
     * 获得指定日期的前n天/后n天
     * @param Date date
     * @param int dayNum (小于0为前n天/大于0为后n天)
     * @return
     */
    public static String getDiffDay(int dayNum,Date date, String toFmt) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.DAY_OF_YEAR, dayNum);
        return formatDate(toFmt, cal.getTime());
    }
    
    /**
     * 获得当前日期的前n天/后n天
     * @param Date date
     * @param int dayNum (小于0为前n天/大于0为后n天)
     * @return
     */
    public static String getDiffYYYYMMDD(int dayNum) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(new Date());
    	cal.add(Calendar.DAY_OF_YEAR, dayNum);
        return formatDate("yyyyMMdd", cal.getTime());
    }
    
    /**
     * 获得指定日期的前n天/后n天
     * @param Date date
     * @param int dayNum (小于0为前n天/大于0为后n天)
     * @return
     * @throws Exception
     */
    public static Date getDiffDay(Date date,int dayNum) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.DAY_OF_YEAR, dayNum);
        return cal.getTime();
    }
    
    /**
     * 获得指定日期的前n月/后n月
     * @param Date date
     * @param int dayNum (小于0为前n月/大于0为后n月)
     * @return
     * @throws Exception
     */
    public static Date getDiffMonth(Date date,int monthNum) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MONTH, monthNum);
        return cal.getTime();
    }
    
    /**
     * 日期格式转化
     * @param date 2013-02-20 00:00
     * @return 201302200000
     */
    public static String toSecondDate(String date) {
    	return date.replace("-", "").replace(" ", "").replace(":", "");
    }
    
    /***
     * 返回星期几，星期日返回7
     * @param fmt
     * @param date
     * @return
     */
    public static int getDayByDateAndFmt(String fmt, String date) {
    	int retObj = -1;
    	Calendar c = Calendar.getInstance();
        Date dat = null;
        try {
        	dat = new SimpleDateFormat(fmt).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(dat);
        retObj = c.get(Calendar.DAY_OF_WEEK);
        if ( retObj > 1 ) {
        	retObj = retObj - 1;
        } else if ( retObj == 1 ) {
        	retObj = 7;
        }
    	return retObj;
    }
    
    /**
     * 根据入口参数的年月日计算周几
     * @param dateStr 日期字符串
     * @param dateFmt 格式字符串
     * @param weekPre 前缀
     * @return  前缀 + (一二三四五六日)
     */
    public static String getWeek(String dateStr,String dateFmt, String weekPre) {
        
        Date dat = getDate(dateStr, dateFmt);
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(dat);
        int w=cal.get(java.util.Calendar.DAY_OF_WEEK)-1;
        String weekString = "";
        String wPre = null == weekPre?"星期":weekPre;
        switch (w) {
	        case 0:{
				weekString = "日";
				break;
			}
			case 1:{
				weekString = "一";
				break;
			}
			case 2:{
				weekString = "二";
				break;
			}
			case 3:{
				weekString = "三";
				break;
			}
			case 4:{
				weekString = "四";
				break;
			}
			case 5:{
				weekString = "五";
				break;
			}
			case 6:{
				weekString = "六";
				break;
			}
        }
		return wPre + weekString;
    }
    
    /**
     * 根据入口参数的年月日计算几时几分
     * @param dateStr 日期字符串 格式：2013-03-07 00:00
     * @return  几时几分 格式为00:00
     */
    public static String getHHMM(String dateStr) {
    	if(dateStr==null||"".equals(dateStr)) return "00:00";
    	if(dateStr.length()<16) return "00:00";
		return dateStr.substring(11);
    }
    
    /**
     * 根据入口参数的年月日计算某月某天几时几分
     * @param dateStr 日期字符串 格式：2013-03-07 00:00
     * @return  几时几分 格式为00:00
     */
    public static String getMDHHMM(String dateStr) {
    	if(dateStr==null||"".equals(dateStr)) return "01-01 00:00";
    	if(dateStr.length()<16) return "01-01 00:00";
		return dateStr.substring(5);
    }
        
	//反回日期
    public static String getWeekOfDate(String date) {
    			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    			Date s_date = null;
    			try {
    				s_date = (Date) sdf.parse(date);
    			} catch (ParseException e) {
    				e.printStackTrace();
    			}
    			String[] weekDays = { "周日 ", "周一 ", "周二 ", "周三 ", "周四 ", "周五 ", "周六 " };
    			Calendar cal = Calendar.getInstance();
    			cal.setTime(s_date);
    			int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
    			if (w < 0)
    				w = 0;
    			return weekDays[w];
    }
    
	//根据中文周几返回数字周几
    public static String getWeekNoByCnweek(String cnweek) {
    	Map<String, String> weekDays = new HashMap<String, String>();
    	weekDays.put("周日", "7");
    	weekDays.put("周一", "1");
    	weekDays.put("周二", "2");
    	weekDays.put("周三", "3");
    	weekDays.put("周四", "4");
    	weekDays.put("周五", "5");
    	weekDays.put("周六", "6");
    	
    	return weekDays.get(cnweek);
    }
	//根据数字周几返回中文周几
    public static String getWeekCnByNoweek(String cnweek) {
    	Map<String, String> weekDays = new HashMap<String, String>();
    	weekDays.put("7", "周日");
    	weekDays.put("1", "周一");
    	weekDays.put("2", "周二");
    	weekDays.put("3", "周三");
    	weekDays.put("4", "周四");
    	weekDays.put("5", "周五");
    	weekDays.put("6", "周六");
    	
    	return weekDays.get(cnweek);
    }
	/**
	 * <p>获得输入日期的当前号数</p>
	 * @param 日期，格式为20130912
	 * @return 12
	 */
	public static String getDayOfYMD(String date) {
		if(date==null) return "1";
		return date.substring(6);
	}
	
	public static void main(String[] args) throws Exception{
//		System.out.println(getNowDateTime());
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(new Date());
//		System.out.println(Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
//		System.out.println(cal.get(Calendar.MINUTE));
//		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)-1);
//		
//		System.out.println(exchange("yyyy-MM-dd","yyyy年MM月dd日","2012年1月1日"));
//		System.out.println(cal.getTime());
//		
//		System.out.println(getDate("2012-05-17 00:00:00","yyyy-MM-dd HH:mm:ss").getTime());
//		
//		System.out.println(getNextDate(new Date()));
//		System.out.println(getBeforDate(new Date()));
		
//		System.out.println(getDayOfYMD("20130220"));
//		System.out.println(getHHMM("2013-03-07 10:00"));
		
		String time = "2013-08-21 16:59:00";
		System.out.print(DateTool.getDiffMinute(1,time));
	}

}
