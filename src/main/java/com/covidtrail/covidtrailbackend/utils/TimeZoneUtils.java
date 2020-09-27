package com.covidtrail.covidtrailbackend.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TimeZoneUtils {
	
	private final static String EST = "EST";
	
	private Date date;
	private String sDate;
	private String formatedDate;
	private String timezone;
	
	public TimeZoneUtils(Date date, String timezone) {
		this.date = date;
		this.timezone = timezone;
	}
	
	public TimeZoneUtils(String sDate, String timezone) {
		this.sDate = sDate;
		this.timezone = timezone;
	}
	
	public TimeZoneUtils toET() {
		
		SimpleDateFormat sdf = new SimpleDateFormat(timezone);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		sdf.setTimeZone(TimeZone.getTimeZone(ZoneId.of("America/Toronto")));
		this.formatedDate = sdf.format(calendar.getTime());
		
		return this;
	}
	
	public TimeZoneUtils toET(String inputFormat) throws ParseException {
		
		SimpleDateFormat sdfInput = new SimpleDateFormat(timezone);
		
		if (sDate != null && !sDate.isEmpty()) {
			date = sdfInput.parse(sDate);
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(timezone);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		sdf.setTimeZone(TimeZone.getTimeZone(ZoneId.of("America/Toronto")));
		this.formatedDate = sdf.format(calendar.getTime());
		
		return this;
	}
	
	public TimeZoneUtils toEST() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		SimpleDateFormat sdf = new SimpleDateFormat(timezone);
		
		sdf.setTimeZone(TimeZone.getTimeZone(EST));
		this.formatedDate = sdf.format(calendar.getTime());
		
		return this;
	}
	
	public String getFormatedDate() {
		return this.formatedDate;
	}

}
