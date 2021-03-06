package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor //전체 생성자
@NoArgsConstructor //빈 생성자
@Data //getter, setter
public class UserProfileDto {
	
//	private int isPageOwner;
	
	private boolean pageOwnerState;
	private int imageCount;
	
	private boolean subscribeState;
	private int subscribeCount;
	
	private User user;
}
