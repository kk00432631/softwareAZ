package com.hcl.utilityFactory.utility;

public class GeneralUtility {
	public static boolean stringNotEmpty(String strValue) {
		if(strValue == null || strValue.isBlank()|| strValue.isEmpty()) {
			return false;
		}
		return true;
	}
}
