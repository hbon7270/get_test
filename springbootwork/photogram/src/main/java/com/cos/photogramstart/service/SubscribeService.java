package com.cos.photogramstart.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper; //qlrm //스프링부트에서 제공하는 라이브러리가 아님 //pom.xml에 따로 라이브러리 등록 필요
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.subscribe.Subscribe;
import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // 생성자 어노테이션
@Service
public class SubscribeService {

	private final SubscribeRepository subscribeRepository; // 생성자 필요
	private final EntityManager em; //Repository는 EntityManager를 구현해서 만들어져 있는 구현체

	@Transactional(readOnly=true) //select 진행하기 때문에 readOnly=true
	public List<SubscribeDto> 구독리스트(int principalId, int pageUserId){
		
		//쿼리준비
		//쿼리문장 실제로 띄어져 있으면 "" 안에 마지막 띄어쓰기 해줘야함!!!!!!!!!!!
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT u.id, u.username, u.profileImageUrl, ");
		sb.append("if((SELECT TRUE FROM subscribe WHERE fromUserId = ? AND TOUserId = u.id), 1,0) subscribeState, ");
		sb.append("if((?=u.id),1,0) equalUserState ");
		sb.append("FROM user u INNER JOIN subscribe s ");
		sb.append("ON u.id = s.toUserId ");
		sb.append("WHERE s.fromUserId = ?"); //세미콜론 첨부하면 안됨
		
		//1. 물음표 principalId
		//2. 물음표 principalId
		//3. 마지막 물음표 pageUserId
		
		//쿼리 완성
		Query query = em.createNativeQuery(sb.toString())
				.setParameter(1, principalId)
				.setParameter(2, principalId)
				.setParameter(3, pageUserId);
				
		//https://mvnrepository.com/artifact/org.qlrm/qlrm/2.1.1 //QLRM		
		//쿼리 실행(qlrm 라이브러리 필요 = Dto에 DB 결과를 매핑하기 위해서)
		JpaResultMapper result = new JpaResultMapper();
		List<SubscribeDto> subscribeDto =  result.list(query, SubscribeDto.class);
		
		return subscribeDto;
	}
	
	
	
//	@Transactional
//	public int 구독하기(int fromUserId, int toUserId) {
	
	
	
	@Transactional
	public void 구독하기(int fromUserId, int toUserId) {
//		int result = subscribeRepository.mSubscribe(fromUserId, toUserId);
		
		try {
			subscribeRepository.mSubscribe(fromUserId, toUserId);
		}catch(Exception e){
			throw new CustomApiException("이미 구독을 하였습니다.");
		}
		
//		subscribeRepository.mSubscribe(fromUserId, toUserId);
		
//		return result;
	}

	
	
	
	@Transactional
	public void 구독취소하기(int fromUserId, int toUserId) {
		subscribeRepository.mUnSubscribe(fromUserId, toUserId);
		
	}
}
