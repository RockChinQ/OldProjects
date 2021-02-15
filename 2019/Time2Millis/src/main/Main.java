package main;

import java.util.Calendar;

public class Main {
	final static int month=0;
	final static int date=1;
	final static int hourOfDay=10;
	final static int minute=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(2019, month-1, date, hourOfDay, minute);
        long millis = calendar.getTimeInMillis();
	}

}
