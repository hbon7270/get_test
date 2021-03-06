package com.cos.photogramstart.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // DI할때 사용
@Controller // 1.IoC 2.파일을 리턴하는 컨트롤러
public class AuthController {

	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	private final AuthService authService;

//	public AuthContoller(AuthService authService) {
//		this.authService=authService;
//	}

	@GetMapping("/auth/signin")
	public String signinForm() { // 로그인 페이지로 이동한다.
		return "auth/signin";
	}

	@GetMapping("/auth/signup")
	public String signupForm() { // 회원가입 페이지로 이동한다.
		return "auth/signup";
	}

	// 회원가입버튼 -> /auth/signup -> /auth/signin
	// 회원가입버튼 -> X //csrf 토큰 때문에 되지 않기 때문에 //SecurityConfig 폴더로 가서 세팅해준다.
	
//	public @ResponseBody String signup(@Valid SignupDto signupDto, BindingResult bindingResult) { // key=value (x-www-form-urlencoded)
		@PostMapping("/auth/signup") // 회원가입이 성공하면 로그인 페이지로 이동한다.
		public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) { // key=value (x-www-form-urlencoded)

		if (bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();

			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				
				System.out.println("=====================================");
				System.out.println(error.getDefaultMessage());
				System.out.println("=====================================");
			}
//			return "오류남";
//			throw new RuntimeException("유효성검사 실패함");
			throw new CustomValidationException("유효성검사 실패함", errorMap);

			
		}else {
			
			User user = signupDto.toEntity();
			User userEntity = authService.회원가입(user);

			System.out.println(userEntity);

			log.info(user.toString());

			return "auth/signin";
			
		}

//		log.info(signupDto.toString());
		// 문자열만!!

		// User <- SignupDto
//		User user = signupDto.toEntity();
//		User userEntity = authService.회원가입(user);
//
//		System.out.println(userEntity);
//
//		log.info(user.toString());
//
//		return "auth/signin";
	}
}
