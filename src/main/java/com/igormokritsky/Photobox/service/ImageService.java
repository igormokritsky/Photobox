package com.igormokritsky.Photobox.service;



import com.igormokritsky.Photobox.model.Image;
import java.util.List;


public interface ImageService {

    List<Image> findByUserId(Long id);

}
