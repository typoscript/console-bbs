package main;

import java.text.SimpleDateFormat;

public class Date {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");

	public static String getCurrentDate() {
		return sdf.format(System.currentTimeMillis());
	}
}
