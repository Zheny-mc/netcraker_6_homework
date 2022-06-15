package ru.netcraker.extraEntityForRequest;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Test {
	public static void main(String[] args) {
		Calendar calendar = new GregorianCalendar();

		Date date1 = new Date();
		Date date2 = new Date();

		System.out.println(date1.getTime());
		System.out.println(date2.getTime());

		System.out.println(date1.equals(date2));
	}
}
