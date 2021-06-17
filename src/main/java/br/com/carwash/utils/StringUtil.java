package br.com.carwash.utils;

public class StringUtil {

	public static String getLikeString(String StringTarget) {
		return "%" + StringTarget.replaceAll(" ", "%")+ "%";
	}

	public static String extractformatedToken(String jwts) {
		return jwts.replace("Bearer ","");
	}

}
