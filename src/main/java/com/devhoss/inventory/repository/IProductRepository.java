package com.devhoss.inventory.repository;

import com.devhoss.inventory.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductRepository extends CrudRepository<Product, Long> {
    @Query("select p from Product p where p.name like %?1%")
    List<Product> findByName(String name);

    List<Product> findByNameContainingIgnoreCase(String name);
}
