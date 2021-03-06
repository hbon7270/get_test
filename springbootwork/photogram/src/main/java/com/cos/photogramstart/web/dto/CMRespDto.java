package com.cos.photogramstart.web.dto;

import java.util.Map;

import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMRespDto<T> {
	// public class CMRespDto{
	
	private int code; //1(성공) / -1(실패)//		
	private String message;
	private T data;
	
//	private Map<String, String> errorMap;

// private User user;
}
