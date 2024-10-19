package com.devhoss.inventory.repository;

import com.devhoss.inventory.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {

  //  Optional<Category> findByDni(String dni);
}