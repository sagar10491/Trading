package com.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Test {

	public static void main(String[] arg) throws ParseException{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("a hh:mm");
		System.out.println(simpleDateFormat.parse("AM 07:00"));
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
	}
}
