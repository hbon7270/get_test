package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomApiException extends RuntimeException{
	
	//객체를 구분할 때
	private static final long serialVersionUID = 1L;
	
//	private String message;
//	private Map<String, String> errorMap;
										/* , Map<String, String>errorMap */
	public CustomApiException(String message) {
		super(message);
//		this.message = message;
//		this.errorMap = errorMap;
	}
	
	
//	public Map<String, String> getErrorMap(){
//		return errorMap;
//	}
	
}
