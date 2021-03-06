package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer>{
	
	@Modifying //INSERT, DELETE, UPDATE를 네이티브 쿼리로 작성하려면 해당 어노테이션 필요!!!
	@Query(value="INSERT INTO subscribe(fromUserId,toUserId,createDate) VALUES(:fromUserId, :toUserId, now())", nativeQuery = true)
//	int mSubscribe(int fromUserId, int toUserId); // -> 함수 //:뒤에 바인딩되고 난 후 실행
	void mSubscribe(int fromUserId, int toUserId);
	//JSP할때 //PrepareStatement함//성공하면 1 //실패하면 -1 //그래서 int를 사용함
								//1 (변경된 행의 개수가 리턴됨) // -1 오류
	@Modifying
	@Query(value="DELETE FROM subscribe WHERE fromUserId=:fromUserId AND toUserId = :toUserId", nativeQuery = true)
//	int mUnSubscribe(int fromUserId, int toUserId); //// -> 함수 //:뒤에 바인딩되고 난 후 실행
	void mUnSubscribe(int fromUserId, int toUserId);
	//JSP할때 //PrepareStatement함//성공하면 1 //실패하면 -1 //그래서 int를 사용함
	
	@Query(value="SELECT COUNT(*) FROM subscribe WHERE fromUserId = :principalId AND toUserId = :pageUserId", nativeQuery=true)
	int mSubscribeState(int principalId, int pageUserId);
						  //로그인한 아이디 //페이지의 아이디
	
	@Query(value="SELECT COUNT(*) FROM subscribe WHERE fromUserId = :pageUserId", nativeQuery=true)
	int mSubscribeCount(int pageUserId);
	
}
