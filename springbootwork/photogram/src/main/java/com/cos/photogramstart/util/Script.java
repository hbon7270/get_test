package com.cos.photogramstart.util;

public class Script {
	
	public static String back(String msg) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		
		sb.append("alert('   " +msg+ "   ');");
		sb.append("history.back();");
		
		sb.append("</script>");
		
		System.out.println("스크립트 뜨냐?"+sb.toString());
		
		return sb.toString();
	}
}
