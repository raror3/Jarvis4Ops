package org.jarvis4ops.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DateHelper {
	private static final Logger log = LoggerFactory.getLogger(DateHelper.class);

	public String getDateInFormat(Date dateToBeFormatted, String dateFormat) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(dateToBeFormatted);
	}

	public long getEpochTime(int days) {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DAY_OF_MONTH, -days);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
		Date forteenDaysAgo = calendar.getTime();
		log.info("Date comes out to be: " + forteenDaysAgo);
		long epoch = forteenDaysAgo.getTime();
		log.info("Date comes out to be: " + epoch);
		return epoch;
	}

}
