package com.igormokritsky.Photobox.web;


import com.igormokritsky.Photobox.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping("/album")
    public String showImage(Model model) {

        long userId = 7; // todo fix issue with getting user id from session

        model.addAttribute("images", imageService.findByUserId(userId));
        return "album";
    }

}
