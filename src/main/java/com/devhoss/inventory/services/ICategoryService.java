package com.devhoss.inventory.services;

import com.devhoss.inventory.model.Category;
import com.devhoss.inventory.response.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {

    public ResponseEntity<CategoryResponseRest> search();
    public ResponseEntity<CategoryResponseRest> searchById(Long id);
    public ResponseEntity<CategoryResponseRest> save(Category category);
    public ResponseEntity<CategoryResponseRest> update(Category category, Long id);
    public ResponseEntity<CategoryResponseRest> deleteById(Long id);

}
