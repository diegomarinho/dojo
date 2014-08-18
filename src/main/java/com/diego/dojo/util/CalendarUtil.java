package com.diego.dojo.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Diego
 * @since 18/08/2014
 */
public class CalendarUtil {
	
	public boolean exibirPartidaEmUmMinuto(Date data, Date data2) {

		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(data);
		
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(data2);

		cal1.add(Calendar.MINUTE, 1);

		return cal1.after(cal2);
	}
}
