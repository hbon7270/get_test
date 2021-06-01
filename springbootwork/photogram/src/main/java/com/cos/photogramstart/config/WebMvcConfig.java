package com.cos.photogramstart.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;



@Configuration
public class WebMvcConfig implements WebMvcConfigurer{ //web 설정 파일
	
	@Value("${file.path}")
	private String uploadFolder;
	
	
	//https://kanetami.tistory.com/99 -> 프로젝트 외부 리소스 불러오기(이미지, 업로드한 파일)
	@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			WebMvcConfigurer.super.addResourceHandlers(registry);
			
			registry				//---> /upload/로 시작하는 페이지에는
			   						//C:/workspace/springbootwork/upload/ 로 바꿔준다.
			 .addResourceHandler("/upload/**") //jsp페이지에서 /upload/** 이런 주소 패턴이 나오면 발동
			 .addResourceLocations("file:///"+uploadFolder)
			 .setCachePeriod(60*10*6) //1시간
			 .resourceChain(true)
			 .addResolver(new PathResourceResolver());
		}
}
