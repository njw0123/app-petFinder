package util;

import java.util.Map;

public class QueryStringBuilder {
	public static String bulid(Map<String, String> params) {
		
		String str = params.toString();
		str = str.replaceAll(", ", "&");
		
		return str.substring(1, str.length()-1);
	}
}
