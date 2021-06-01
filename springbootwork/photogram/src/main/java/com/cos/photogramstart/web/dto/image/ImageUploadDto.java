package com.cos.photogramstart.web.dto.image;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class ImageUploadDto {
	@NotBlank
	private MultipartFile file;
	private	String cation;
	
	public Image toEntity(String postImageUrl, User user) {
		return Image.builder()
				.caption(cation)
				.postImageUrl(postImageUrl)
				.user(user)
				.build();
	}	
}
