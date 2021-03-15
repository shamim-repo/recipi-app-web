package com.shamim.repo.recipe.service;

import com.shamim.repo.recipe.domain.Category;
import java.util.Set;

public interface CategoryService {
    Set<Category> getCategories();
}
