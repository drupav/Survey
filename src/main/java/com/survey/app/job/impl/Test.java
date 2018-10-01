package com.survey.app.job.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;

public class Test {
	 private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm",Locale.ENGLISH);
	 private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a",Locale.ENGLISH);
public static void main(String[] args) throws IOException, ParseException {
	System.out.println(dateTimeFormat.parse("sep 18, 2018 21:30:25 AM"));
	
	 LocalDateTime strDate = LocalDateTime.parse("9/3/2018 21:30",
			 DateTimeFormat.forPattern("MM/DD/yyyy HH:mm"));
	 System.out.println(strDate);
}
}
	