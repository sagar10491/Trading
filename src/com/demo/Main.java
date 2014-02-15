package com.demo;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
	 
	public static void main(String[] args) {
		Timer timer = new Timer();
		TimerTask tt = new TimerTask(){
			public void run(){
				Calendar cal = Calendar.getInstance(); //this is the method you should use, not the Date(), because it is desperated.
 
				int hour = cal.get(Calendar.HOUR_OF_DAY);//get the hour number of the day, from 0 to 23
 
				if(hour == 1){
					System.out.println("doing the scheduled task");
				}
			}
		};
		timer.schedule(tt, 1000, 1000*5);//	delay the task 1 second, and then run task every five seconds
		Calendar calendar = new GregorianCalendar();
		System.out.println(calendar.get(Calendar.HOUR));

		TimeZone timeZone = TimeZone.getTimeZone("Europe/London");
		calendar.setTimeZone(timeZone);
		System.out.println(calendar.get(Calendar.HOUR));
	}
}