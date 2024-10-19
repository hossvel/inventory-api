package com.devhoss.inventory.controller;


import com.devhoss.inventory.model.Category;
import com.devhoss.inventory.response.CategoryResponseRest;
import com.devhoss.inventory.services.ICategoryService;
import com.devhoss.inventory.util.CategoryExcelExporter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryRestController {

    @Autowired
    private ICategoryService categoryService;

    // get de todas las categorias
    @GetMapping
    public ResponseEntity<CategoryResponseRest> searchCategories() {

        ResponseEntity<CategoryResponseRest> response = categoryService.search();
        return response;
    }

    // get de una categoria por id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseRest> searchCategoriesById(@PathVariable Long id) {

        ResponseEntity<CategoryResponseRest> response = categoryService.searchById(id);
        return response;
    }

    @PostMapping
    public ResponseEntity<CategoryResponseRest> save(@RequestBody Category category) {

        ResponseEntity<CategoryResponseRest> response = categoryService.save(category);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseRest> update(@RequestBody Category category, @PathVariable Long id) {

        ResponseEntity<CategoryResponseRest> response = categoryService.update(category, id);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryResponseRest> delete(@PathVariable Long id) {

        ResponseEntity<CategoryResponseRest> response = categoryService.deleteById(id);
        return response;
    }

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=categorias.xlsx";
        response.setHeader(headerKey, headerValue);

        ResponseEntity<CategoryResponseRest> categoryResponse = categoryService.search();
        CategoryExcelExporter excelExporter = new CategoryExcelExporter(categoryResponse.getBody().getCategoryResponse().getCategory());
        excelExporter.export(response);

    }



}
