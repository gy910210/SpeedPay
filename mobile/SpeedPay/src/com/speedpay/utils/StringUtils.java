package com.speedpay.utils;

public class StringUtils {
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))&&(str.charAt(i)!='.')) {
				return false;
			}
		}
		return true;
	}

}
