package com.shamim.repo.recipe.controller;

import com.shamim.repo.recipe.domain.Category;
import com.shamim.repo.recipe.domain.UnitOfMeasure;
import com.shamim.repo.recipe.repository.CategoryRepository;
import com.shamim.repo.recipe.repository.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"","/","index","index.html"})
    public String getIndexPage(){

        Optional<Category> categoryOptional= categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional= unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Cat Id is :"+categoryOptional.get().getId());
        System.out.println("UM Id is :"+unitOfMeasureOptional.get().getId());
        return "index";
    }
}
