package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity //해당 파일로 시큐리티를 활성화
@Configuration //IoC  
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super 삭제 - 기존 시큐리티가 가지고 있는 기능이 다 비활성화됨.
		
		
		http.csrf().disable();
		
		
		http.authorizeRequests()
		.antMatchers("/","/user/**","/image/**","/subscribe/**","/comment/**","/api/**").authenticated() //해당 주소들로 요청할 시 권한을 부여한다.
		.anyRequest().permitAll() //그 나머지 주소들은 허용한다.
		
		.and()
		.formLogin()
		.loginPage("/auth/signin") //위에 해당하는 주소들로 요청하면 /auth/signin 접속할 수 있게 해주겠다. //GET
		.loginProcessingUrl("/auth/signin") //POST -> 스프링 시큐리티가 로그인 프로세스 진행
		.defaultSuccessUrl("/");
	}

	
	
}
