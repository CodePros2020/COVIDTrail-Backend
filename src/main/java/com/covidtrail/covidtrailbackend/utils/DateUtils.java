package com.covidtrail.covidtrailbackend.utils;

import java.util.Date;

public class DateUtils extends TimeZoneUtils{

	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd hh:mm:ss";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
	
	public static TimeZoneUtils to_YYYY_MM_DD_HH_MM_SS(Date date) {
		return new TimeZoneUtils(date, YYYY_MM_DD_HH_MM_SS);
	}
	
	public static TimeZoneUtils to_YYYY_MM_DD_HH_MM(Date date) {
		return new TimeZoneUtils(date, YYYY_MM_DD_HH_MM);
	}
	
	public static TimeZoneUtils to_YYYY_MM_DD(Date date) {
		return new TimeZoneUtils(date, YYYY_MM_DD);
	}
	
	public static TimeZoneUtils to_YYYY_MM_DD(String date) {
		return new TimeZoneUtils(date, YYYY_MM_DD);
	}
	
	public static TimeZoneUtils to_YYYY_MM_DD_HH_MM(String date) {
		return new TimeZoneUtils(date, YYYY_MM_DD_HH_MM);
	}

}
