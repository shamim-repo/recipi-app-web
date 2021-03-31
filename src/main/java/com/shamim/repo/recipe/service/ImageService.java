package com.shamim.repo.recipe.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImage(Long aLong, MultipartFile imageFile);
}
