package com.cos.photogramstart.web.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeDto {
	private int userId; 
	private String username; //유저네임
	private String profileImageUrl; //사진
	
	private Integer subscribeState; //구독상태
	private Integer equalUserState; //동일한 유저 상태
}
