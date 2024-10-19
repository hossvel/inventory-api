package com.devhoss.inventory.repository;

import com.devhoss.inventory.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryRepository extends CrudRepository<Category, Long> {
}
