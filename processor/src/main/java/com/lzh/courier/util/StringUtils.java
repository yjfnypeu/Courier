package com.lzh.courier.util;


public class StringUtils {

	public static boolean isEmpty(CharSequence data) {
		if (data == null || data.length() == 0) {
			return true;
		}
		return false;
	}
	
	public static String getSetMethodName(String fieldName) {
		checkName(fieldName);
		
		StringBuffer sb = getUpperMethod(fieldName);
		return "set" + sb.toString();
	}
	
	private static StringBuffer getUpperMethod(String fieldName) {
		char[] charArray = fieldName.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < charArray.length; i++) {
			if (i == 0) {
				sb.append(Character.toUpperCase(charArray[i]));
			} else {
				sb.append(charArray[i]);
			}
		}
		return sb;
	}
	
	public static String getGetMethodName(String fieldName) {
		checkName(fieldName);
		
		StringBuffer sb = getUpperMethod(fieldName);
		return "get" + sb.toString();
	}

	private static void checkName(String fieldName) {
		if (isEmpty(fieldName)) {
			throw new IllegalArgumentException("method name is null");
		}
	}
	
	public static String getIsMethodName(String fieldName) {
		checkName(fieldName);
		
		StringBuffer sb = getUpperMethod(fieldName);
		return "is" + sb.toString();
	}
	
	public static String spliteFieldName(String methodName) {
		if (isEmpty(methodName)) {
			return methodName;
		}
		
		if (methodName.startsWith("is")) {
			methodName = methodName.replaceFirst("is", "");
		} else if (methodName.startsWith("set")) {
			methodName = methodName.replaceFirst("set", "");
		} else if (methodName.startsWith("get")) {
			methodName = methodName.replaceFirst("get", "");
		}
		
		return getUpperMethod(methodName).toString();
		
	}

}
