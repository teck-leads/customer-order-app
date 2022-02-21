package com.techleads.app.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;

public class TestApp {

	public static void main(final String[] args) {
		
		double price =500.00;
		double discount=7;
		double applyDiscount = applyDiscount(price, discount);
		System.out.println(applyDiscount);
		
		System.exit(0);
		LocalDate today = LocalDate.now();
		System.out.println("Is weekend : " + isWeekend(today));

		LocalDate someDate = LocalDate.of(2022, Month.FEBRUARY, 20); // 2nd-Jan-2021

		System.out.println("Is weekend : " + isWeekend(someDate));
		LocalDate someDate1 = LocalDate.of(2022, Month.FEBRUARY, 22);
		System.out.println("Is weekend : " + isWeekend(someDate1));
	}

	public static boolean isWeekend(final LocalDate ld) {
		DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
		return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
	}
	
	
	public static double applyDiscount(double price, double discount) {
		return (price*((double)discount/100));
	}

}
