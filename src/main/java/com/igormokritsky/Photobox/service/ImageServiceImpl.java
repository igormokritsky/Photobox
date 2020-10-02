package com.igormokritsky.Photobox.service;


import com.igormokritsky.Photobox.model.Image;
import com.igormokritsky.Photobox.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Override
    public List<Image> findByUserId(Long id) {
        return imageRepository.findImagesByUserId(id);
    }

}
