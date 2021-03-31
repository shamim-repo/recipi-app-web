package com.shamim.repo.recipe.controller;

import com.shamim.repo.recipe.commands.RecipeCommand;
import com.shamim.repo.recipe.service.ImageService;
import com.shamim.repo.recipe.service.RecipeService;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.springframework.util.StreamUtils.copy;


@Controller
public class ImageController {
    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{recipeId}/image")
    public String showUploadForm(@PathVariable String recipeId, Model model) throws NotFoundException {
        model.addAttribute("recipe",recipeService.findRecipeCommandById(Long.valueOf(recipeId)));
        return "recipe/imageuploadform";
    }

    @GetMapping("recipe/{recipeId}/recipeImage")
    public void renderImageFromDB(@PathVariable String recipeId, HttpServletResponse response) throws IOException, NotFoundException {
        RecipeCommand recipeCommand=recipeService.findRecipeCommandById(Long.valueOf(recipeId));

        byte[] bytes=new byte[recipeCommand.getImage().length];
        int i=0;
        for (Byte b:recipeCommand.getImage()){
            bytes[i++]=b;
        }
        response.setContentType("image/jpeg");
        InputStream inputStream= new ByteArrayInputStream(bytes);
        copy(inputStream,response.getOutputStream());

    }

    @PostMapping("recipe/{recipeId}/image")
    public String saveImage(@PathVariable String recipeId, @RequestParam("imagefile")MultipartFile file){
        imageService.saveImage(Long.valueOf(recipeId),file);
        return "redirect:/recipe/"+recipeId+"/show";
    }
}
