package com.cos.photogramstart.domain.image;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.subscribe.Subscribe;
import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data 
@Entity
public class Image {//N, 1 //여러개의 이미지 업로드 //한명의 유저가
	@Id //기본키 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	private String caption; //프로젝트 완성할 수 있을까? 이런 내용 들어가는 컬럼
	private String postImageUrl; //사진을 전송받아 그 사진을 서버를 통해 특정 폴더에 저장 - DB에 그 저장된 경로를 insert
	
	@JoinColumn(name="userId")
	@ManyToOne(fetch=FetchType.EAGER)
	private User user; //누가 업로드 했는지 확인하는 컬럼 //1
	
	//이미지 좋아요
	
	//댓글
	
	private LocalDateTime createDate; 
	
	@PrePersist 
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
	//오브젝트를 콘솔에 출력할 때 문제가 될 수 있어서 User 부분을 출력되지 않게 함.
//	@Override
//	public String toString() {
//		return "Image [id=" + id + ", caption=" + caption + ", postImageUrl=" + postImageUrl 
//				+ ", createDate=" + createDate + "]";
//	}
	
	
}
