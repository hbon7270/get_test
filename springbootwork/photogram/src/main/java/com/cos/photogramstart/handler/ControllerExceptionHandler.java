package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {
	
//	@ExceptionHandler(RuntimeException.class)

//	public String validationException(CustomValidationException e) {
//	public String validationException(RuntimeException e) {

//		public Map<String,String> validationException(CustomValidationException e) {

	
//		public CMRespDto validationException(CustomValidationException e) {
//	public CMRespDto<Map<String, String>> validationException(CustomValidationException e) {
//	public CMRespDto<String> validationException(CustomValidationException e) {
		
		
//		public CMRespDto<?> validationException(CustomValidationException e) {
	
		
	
		
		@ExceptionHandler(CustomValidationException.class)
		public String validationException(CustomValidationException e) {

			
			
//		return e.getMessage();
//		return e.getErrorMap();
//		
//		return new CMRespDto<Map<String,String>>(-1,e.getMessage(),e.getErrorMap());
		
			
			
		//CMRespDto, Script 비교
		//1. 클라이언트에게 응답할 때는 Script 좋음.
		//2. Ajax통신 - CMRespDto
		//3. Android 통신 - CMRespDto
			
		System.out.println("왜 안되는거니?????????????????????!!!!!!!!!!!!!!!");	
		
		if(e.getErrorMap()==null) {
			
			System.out.println(e.getErrorMap());
			
			return Script.back(e.getMessage());
		}else {
			
			System.out.println(e.getErrorMap());
			
			return Script.back(e.getErrorMap().toString());
		}
   }
		
		
		@ExceptionHandler(CustomException.class)
		public String exception(CustomException e) {
			return Script.back(e.getMessage());
		}
		
		
		
		
//		@ExceptionHandler(CustomException.class)
//		public String exception(CustomException e) {
//			return Script.back(e.getMessage());	
//	}
		
		
		
		
		
		
		//***오브젝트 CMRespDto 응답***//
//		@ExceptionHandler(CustomValidationApiException.class)
//		public CMRespDto<?> validationApiException(CustomValidationApiException e) {
//		@ExceptionHandler(CustomValidationApiException.class)
//		public ResponseEntity<CMRespDto<?>> validationApiException(CustomValidationApiException e) {
//		return new CMRespDto<>(-1,e.getMessage(), e.getErrorMap());
								//-1 : 실패
		
		@ExceptionHandler(CustomValidationApiException.class)
		public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
							//? 할 시 제네릭 타입 자동 응답이 됨!!
			
			System.out.println("=================================================================실행확인!!!");
			
			return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(), e.getErrorMap()),HttpStatus.BAD_REQUEST);
//			return new ResponseEntity<CMRespDto<?>>(HttpStatus.BAD_REQUEST, new CMRespDto<>(-1,e.getMessage(), e.getErrorMap());
							
	}
		
		@ExceptionHandler(CustomApiException.class)
		public ResponseEntity<?> apiException(CustomApiException e) {
			return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),null),HttpStatus.BAD_REQUEST);
							
	}
}
