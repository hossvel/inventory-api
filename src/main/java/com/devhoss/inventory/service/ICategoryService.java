package com.devhoss.inventory.service;

import com.devhoss.inventory.models.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    Category save(Category category);
}
