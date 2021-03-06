package com.cos.photogramstart.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	
		
	private final UserService userService; //UserService DI해주고
	
	@GetMapping("/user/{pageUserId}")
	public String profile(@PathVariable int pageUserId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		UserProfileDto dto = userService.회원프로필(pageUserId, principalDetails.getUser().getId());
		model.addAttribute("dto", dto);
		return "user/profile";
		
	}
	
//	@GetMapping("/user/{id}")
//	public String profile(@PathVariable int id, Model model) {
//		User userEntity = userService.회원프로필(id);
//		model.addAttribute("user", userEntity);
//		return "user/profile";
//		
//	}
	
	@GetMapping("/user/{id}/update")
	public String updateForm(@PathVariable int id, 					/* , Model model */
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		//1. 편함 - 어노테이션 @AuthenticationPrincipal 활용
		//System.out.println("세션 정보 : " + principalDetails.getUser());
		
		
		//2. 어려움
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//PrincipalDetails mPrincipalDetails = (PrincipalDetails) auth.getPrincipal();
		
		//System.out.println("직접 찾은 세션 정보:" + mPrincipalDetails.getUser());
		
//		model.addAttribute("principal", mPrincipalDetails.getUser());
		return "user/update";
	}
}
