package com.lima.test;

import java.util.TimeZone;

public class Test {
	
	public static void main(String[] args) {
		TimeZone london = TimeZone.getTimeZone("Europe/London");
		long now = System.currentTimeMillis();
		System.out.println(now +"   "+ london.getOffset(now));
				
	}
}