package com.cos.photogramstart.domain.subscribe;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(
		uniqueConstraints = {
				@UniqueConstraint(
						name="subscribe_uk",
						columnNames= {"fromUserId", "toUserId"}//실제 데이터베이스 컬럼명
		)
	}
)//두개 이상 컬럼 유니크 줄때 사용!!!

public class Subscribe {
	@Id //기본키 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	@JoinColumn(name="fromUserId") //강제적으로 컬럼명 정함
	@ManyToOne //User는 1 //Subscribe는 M
	private User fromUser;
	
	@JoinColumn(name="toUserId") //강제적으로 컬럼명 정함
	@ManyToOne //User는 1 //Subscribe는 M
	private User toUser;
	
	private LocalDateTime createDate; 
	
	@PrePersist 
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
