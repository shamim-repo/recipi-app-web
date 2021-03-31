package com.shamim.repo.recipe.service;

import com.shamim.repo.recipe.domain.Recipe;
import com.shamim.repo.recipe.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
@Slf4j
@Service
public class ImageServiceImpl implements ImageService{

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @Override
    @Transactional
    public void saveImage(Long aLong, MultipartFile imageFile) {
            try {
                Recipe recipe=recipeRepository.findById(aLong).get();

                Byte[] bytes=new Byte[imageFile.getBytes().length];
                int i=0;
                for (byte b:imageFile.getBytes()){
                    bytes[i++]=b;
                }

                recipe.setImage(bytes);
                recipeRepository.save(recipe);

            }catch (IOException e){
                log.error("Error occurred "+e);
                e.printStackTrace();
            }
    }
}
