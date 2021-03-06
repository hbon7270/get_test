package com.cos.photogramstart.service;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final SubscribeRepository subscribeRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
		
	@Transactional(readOnly = true)
	public UserProfileDto 회원프로필(int pageUserId, int principalId) {
		UserProfileDto dto = new UserProfileDto();
		
		
		User userEntity = userRepository.findById(pageUserId).orElseThrow(()->{
			throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
		});
		
		dto.setUser(userEntity);
//		dto.setIsPageOwner(pageUserId==principalId ? 1 : -1); //1은 페이지 주인, -1은 주인이 아님
//		                          //삼항연산자 //같으면 1 // 다르면 -1
		
		dto.setPageOwnerState(pageUserId==principalId);
//		UserProfileDto의 PageOwner을 boolean 형으로 주면 굳이 삼항연산 필요없음!!! //참,거짓만 판별
		
		dto.setImageCount(userEntity.getImages().size());
		
		int subscribeState = subscribeRepository.mSubscribeState(pageUserId, principalId);
		int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);
		
		dto.setSubscribeState(subscribeState==1);
		dto.setSubscribeCount(subscribeCount);
		
		
		return dto;
	}
		
//	public User 회원프로필(int userId) {
//		// SELECT * FROM image WHERE userId = :userId;
//		User userEntity = userRepository.findById(userId).orElseThrow(()-> {
//			throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
//		}); 
//		
//		System.out.println(userEntity);
//		
//		System.out.println("======================================");
//		
//		userEntity.getImages().get(0);
//		
//		System.out.println("사진 들어가는지 테스트해보기===================:"+userEntity.getImages().size());
//		
//		System.out.println("조인안되는건가???"+userEntity.getImages().get(0));
//		
//		return userEntity;
//	}
	
	
	@Transactional
	public User 회원수정(int id, User user) {
		
		
		User userEntity = userRepository.findById(id).orElseThrow(()->{
			return new CustomValidationApiException("찾을 수 없는 id입니다.");
		});
		
		
		
		
		//1.영속화			//findById(id)에서 id를 찾을때, 없으면 null로 찾기 때문에 //get()를 붙여준다.
//		User userEntity = userRepository.findById(10).get(); //Optional //1. 무조건 찾았다. 걱정마 get()
																		//2. 못찾았어 익섹션 발동시킬게 orElseThrow()
		
//		User userEntity = userRepository.findById(10).get().orElseThrow(new Supplier<IllegalArgumentException>() {
//			
//			@Override
//			public IllegalArgumentException get() {
//				// TODO Auto-generated method stub
//				return new IllegalArgumentException("찾을 수 없는 id입니다.");
//			}
//		});
		
		
		
		
		

//		userEntity.setName(user.getName());
//		userEntity.setPassword(user.getPassword());
//		userEntity.setBio(user.getBio());
//		userEntity.setWebsite(user.getWebsite());
//		userEntity.setPhone(user.getPhone());
//		userEntity.setGender(user.getGender());
		
		//2.영속화된 오브젝트를 수정 - 더티체킹(업데이트 완료)
		
		userEntity.setName(user.getName());
		
		
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		
		
//		userEntity.setPassword(user.getPassword());
		
		userEntity.setPassword(encPassword);
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());
		return userEntity;	
	}//더티체킹이 일어나서 업데이트가 완료됨.
}
