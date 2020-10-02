package com.igormokritsky.Photobox.repository;


import com.igormokritsky.Photobox.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query(
            value = "SELECT * FROM login_schema.images WHERE user_id = ?",
            nativeQuery = true
    )
    List<Image> findImagesByUserId(Long id);

}
