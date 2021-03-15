package com.shamim.repo.recipe.service;

import com.shamim.repo.recipe.domain.Category;
import com.shamim.repo.recipe.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class CategoryServiceImpl implements CategoryService{
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getCategories() {
        Set<Category> categorySet=new HashSet<>();
        categoryRepository.findAll().iterator().forEachRemaining(categorySet::add );
        return categorySet;
    }
}
