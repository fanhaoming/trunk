package com.trunk.core.utils;


import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * @author fanhaoming
 * @Description TODO
 * @Version
 **/
public class DateHelper {

	static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

	public static Date GetUTCdatetimeAsDate() {
		// note: doesn't check for null
		return StringDateToDate(GetUTCdatetimeAsString());
	}

	public static String GetUTCdatetimeAsString() {
		final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		final String utcTime = sdf.format(new Date());
		return utcTime;
	}

	public static String GetUTCdatetimeAsString(Date date) {
		final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		final String utcTime = sdf.format(date);
		return utcTime;
	}

	public static Date getYesterDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		if(date != null)
			calendar.setTime(date);
		calendar.add(Calendar.DATE,-1);
		return calendar.getTime();
	}

	public static Date getTomorrow(Date date) {
		Calendar calendar = Calendar.getInstance();
		if(date != null)
			calendar.setTime(date);
		calendar.add(Calendar.DATE,1);
		return calendar.getTime();
	}

	public static Date StringDateToDate(String StrDate) {
		Date dateToReturn = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATEFORMAT);

		try {
			dateToReturn = (Date) dateFormat.parse(StrDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dateToReturn;
	}

	public static Date StringDateToDate(String StrDate, String format) {
		Date dateToReturn = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		try {
			dateToReturn = (Date) dateFormat.parse(StrDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateToReturn;
	}

	public static String DateToString(Date date, String format) {
		DateFormat format1 = new SimpleDateFormat(format);
		return format1.format(date);
	}

	public static int getDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		return day;
	}

	public static int getTwoDateCount(Date date1, Date date2) {

		Calendar calst = Calendar.getInstance();

		Calendar caled = Calendar.getInstance();

		calst.setTime(date1);
		caled.setTime(date2);

		// 设置时间为0时
		calst.set(Calendar.HOUR_OF_DAY, 0);
		calst.set(Calendar.MINUTE, 0);
		calst.set(Calendar.SECOND, 0);
		caled.set(Calendar.HOUR_OF_DAY, 0);
		caled.set(Calendar.MINUTE, 0);
		caled.set(Calendar.SECOND, 0);
		// 得到两个日期相差的天数
		int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;

		return days;
	}

	public static int getTwoDateHourCount(Date date1, Date date2) {
		long from = date1.getTime();
		long to = date2.getTime();
		int hours = (int) ((to - from) / (1000 * 60 * 60));
		return hours;
	}

	public static int getTwoDateMinuteCount(Date date1, Date date2) {
		long from = date1.getTime();
		long to = date2.getTime();
		int minutes = (int) ((to - from) / (1000 * 60));
		return minutes;
	}

	public static Date convertTodayMin(Date date) {

		String mintoday = DateHelper.DateToString(date, "yyyy-MM-dd 00:00:00");
		date = DateHelper.StringDateToDate(mintoday, "yyyy-MM-dd HH:mm:ss");
		return date;
	}

	public static Date convertTodayMax(Date date) {

		String maxtoday = DateHelper.DateToString(date, "yyyy-MM-dd 23:59:59");
		date = DateHelper.StringDateToDate(maxtoday, "yyyy-MM-dd HH:mm:ss");
		return date;
	}

	public static List<Date> getTwoDaysBetween(Date date1, Date date2) {
		List<Date> dates = new ArrayList<Date>();

		Calendar startDay = Calendar.getInstance();
		Calendar endDay = Calendar.getInstance();

		date1 = DateHelper.convertTodayMin(date1);
		date2 = DateHelper.convertTodayMin(date2);

		startDay.setTime(date1);
		endDay.setTime(date2);

		if (startDay.compareTo(endDay) >= 0) {
			return dates;
		}
		// 现在打印中的日期
		Calendar currentDay = startDay;
		while (true) {
			// 日期加一
			currentDay.add(Calendar.DATE, 1);
			// 日期加一后判断是否达到终了日，达到则终止打印

			int d = currentDay.compareTo(endDay);
			if (d == 0) {
				break;
			}
			dates.add(currentDay.getTime());
		}

		return dates;
	}

	public static Date getFirstDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		return cal.getTime();
	}

	public static Date getLastDayOfWeek(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值

		cal.add(Calendar.DATE, 6);
		return cal.getTime();
	}

	public static Date getFirstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	public static Date getLastDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static Date addMonths(Date date, int months) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, months);
		return cal.getTime();
	}

	public static Date addYears(Date date, int years) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, years);
		return cal.getTime();
	}

	public static int getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DATE);
	}

	public static Date setDay(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, day);
		return cal.getTime();
	}

	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}

	public static Date setMonth(Date date, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		month--;
		cal.set(Calendar.MONTH, month);
		return cal.getTime();
	}

	public static Date setYear(Date date, int year) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.YEAR, year);
		return cal.getTime();
	}

	public static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}
	
	
	public static String dateToStamp(Date date) throws ParseException{
        long ts = date.getTime();
        return String.valueOf(ts);
    }
	
	public static Date stampToDate(String s){
        long lt = new Long(s);
        Date date = new Date(lt);
        return date;
    }

    /**
    * @Description 获取以format格式date日期往前推count个dateType单位的日期数组
    * @Param [format, date, count, dateType]
    * @Return java.lang.String[]
    **/
	public static String[] getLastDate(String format,Date date,Integer count,int dateType){
		List<String> resultList = getLastDate(format,date,count,dateType,String.class,false);
		String[] results = new String[resultList.size()];
		resultList.toArray(results);
		return results;
	}

	/**
	* @Description 获取以format格式date日期往前推count个dateType单位的日期集合，
	 * 返回类型为returnType
	 * isContain表示是否包含当前日期
	* @Param [format, date, count, dateType, returnType, isContain]
	* @Return java.util.List<T>
	**/
	public static <T> List<T> getLastDate(String format,Date date,Integer count,int dateType,Class<T> returnType,boolean isContain){
		//如果返回类型为空，则默认为String格式返回
		if(returnType == null){
			returnType = (Class<T>) String.class;
		}

		List result = null;
		int unitNum = 1;//加减天数单位，默认1天单位
		count = (count != null && count!=0) ?count:12;
		format = StringUtils.isBlank(format)?"yyyyMMdd":format;
		date = (date == null) ? new Date() :date;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		if(count < 0){
			unitNum = -1;//如果加减天数为负数，则是后推天数
			count = Math.abs(count);//count天数负数转正数
		}

		if(!isContain)//如果不包含指定日期，则提前加减日期
			calendar.add(dateType,unitNum);

		if(returnType == String.class){//如果返回类型为字符串，则返回指定格式的日期字符串集合
			result = new ArrayList<String>();
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			for(int i = 1;i<=count;i++){
				result.add(sdf.format(calendar.getTime()));
				calendar.add(dateType,unitNum);
			}
		} else if (returnType == Date.class){
			result = new ArrayList<Date>();
			for(int i = 1;i<=count;i++){
				result.add(calendar.getTime());
				calendar.add(dateType,unitNum);
			}
		}
		return result;
	}

	/**
	* @Description 获取date日期前num个timeType时间单位的日期
	* @Param [timeType, num, date]timeType可用Calender的单位，如Calendar.Month
	* @Return java.util.Date
	**/
	public static Date getDateBefore(int timeType,int num,Date date){
		if(date == null){
			date = new Date();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(timeType,num);
		return cal.getTime();
	}

	/**
	* @Description
	* @Param [date, format, returnType] 获取日期所属星期的日期列表
	* @Return java.util.List<T>
	**/
	public static <T> List<T> getDateBelongWeek(Date date,String format,Class<T> returnType){
		Date firstDayOfWeek = getFirstDayOfWeek(date);//获取
		return getLastDate(format,firstDayOfWeek,7,Calendar.DATE,returnType,true);
	}


	public static <T> List<T> getDateBelongMonth(Date date,String format,Class<T> returnType){
		Date firstDayOfMonth = getFirstDayOfMonth(date);//获取一个月的最后一天
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int monthNum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);//获取当月天数
		return getLastDate(format,firstDayOfMonth,monthNum,Calendar.DATE,returnType,true);
	}

	public static Date getFirstDayOfYear(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH,0);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		return calendar.getTime();
	}

	public static Date getLastDayOfYear(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH,11);
		calendar.set(Calendar.DAY_OF_MONTH,31);
		return calendar.getTime();
	}


}
