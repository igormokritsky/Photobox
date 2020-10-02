package com.igormokritsky.Photobox;

import com.igormokritsky.Photobox.model.Image;
import com.igormokritsky.Photobox.repository.ImageRepository;
import com.igormokritsky.Photobox.service.ImageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PhotoboxApplicationTests implements ImageService {

	@Autowired
	ImageRepository imageRepository;

	@Override
	public List<Image> findByUserId(Long id) {
		return imageRepository.findImagesByUserId(id);
	}

	@Test
	void contextLoads() {
		System.out.println(findByUserId(7L));
	}

}
