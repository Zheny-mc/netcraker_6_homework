package ru.netcraker.extraEntityForRequest;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Test {
	public static void main(String[] args) {
		Calendar calendar = new GregorianCalendar();

		System.out.println("calendar " + calendar.getTime().toString().split(" ")[1]);
		
		for (int i = 0; i < 17; i++) {
			System.out.println("calendar = " + i + " : " + calendar.get(i));
		}
	}
}
