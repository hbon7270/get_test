package com.cos.photogramstart.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.service.SubscribeService;
import com.cos.photogramstart.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

//@Controller //파일명 컨트롤 할때 사용하는 어노테이션
@RequiredArgsConstructor
@RestController //데이터 컨트롤 할때 사용하는 어노테이션
public class SubscribeApiController {

	private final SubscribeService subscribeService;
	
	@PostMapping("/api/subscribe/{toUserId}") //구독하기
	public ResponseEntity<?> subscribe
	(
			@AuthenticationPrincipal PrincipalDetails principalDetails,
			@PathVariable int toUserId
	)
	{	
//		int result = subscribeService.구독하기(principalDetails.getUser().getId(),toUserId);
		subscribeService.구독하기(principalDetails.getUser().getId(),toUserId);
		return new ResponseEntity<>(new CMRespDto<>(1,"구독하기 성공",null),HttpStatus.OK);
	}
	
	@DeleteMapping("/api/subscribe/{toUserId}") //구독취소하기
	public ResponseEntity<?> unsubscribe
	(
			@AuthenticationPrincipal PrincipalDetails principalDetails,
			@PathVariable int toUserId	
	)
	{	
//		int result = subscribeService.구독취소하기(principalDetails.getUser().getId(),toUserId);
		subscribeService.구독하기(principalDetails.getUser().getId(),toUserId);
		return new ResponseEntity<>(new CMRespDto<>(1,"구독취소하기 성공",null),HttpStatus.OK);
	}

}
