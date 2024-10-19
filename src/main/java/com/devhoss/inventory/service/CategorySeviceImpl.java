package com.devhoss.inventory.service;

import com.devhoss.inventory.models.Category;
import com.devhoss.inventory.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorySeviceImpl implements ICategoryService{

    @Autowired
    private ICategoryRepository iCategoryRepository;
    @Override
    public List<Category> findAll() {
       return iCategoryRepository.findAll();

    }

    @Override
    public Category save(Category category) {
       return iCategoryRepository.save(category);
  }
}
