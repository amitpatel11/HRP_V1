package com.hrp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HrpUtil {

	public static Boolean isEmptyString(String str) {
		Boolean flag = true;
		if (str != null) {
			String trimedStr = str.trim();

			if (trimedStr.length() > 0) {
				flag = false;
			}
		}
		return flag;
	}

	public static Boolean isRegexTrue(String regexStr, String str) {
		if (!isEmptyString(str)) {
			Pattern pattern = Pattern.compile(regexStr);
			Matcher matcher = pattern.matcher(str);
			return matcher.matches();
		} else {
			return false;
		}
	}

}
