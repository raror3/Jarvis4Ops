package org.jarvis4ops;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class JavaPoc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DAY_OF_MONTH, -14);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
		Date forteenDaysAgo = calendar.getTime();
		System.out.println("Date comes out to be: " + forteenDaysAgo);
		long epoch = forteenDaysAgo.getTime();
		System.out.println("Date comes out to be: " + epoch);
	}

}
