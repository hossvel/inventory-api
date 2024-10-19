package com.devhoss.inventory.controller;

import com.devhoss.inventory.model.Product;
import com.devhoss.inventory.repository.ICategoryRepository;
import com.devhoss.inventory.response.ProductResponseRest;
import com.devhoss.inventory.services.IProductService;
import com.devhoss.inventory.util.ProductExcelExport;
import com.devhoss.inventory.util.Util;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {
    @Autowired
    private IProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseRest> save(
            @RequestParam("picture")MultipartFile picture,
            @RequestParam("name")String name,
            @RequestParam("price")int price,
            @RequestParam("stock")int stock,
            @RequestParam("categoryId")Long categoryId) throws IOException
    {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        product.setPicture(Util.compressZLib(picture.getBytes()));

        ResponseEntity<ProductResponseRest> response = productService.save(product, categoryId);


        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseRest> searchById(@PathVariable Long id){
        ResponseEntity<ProductResponseRest> response = productService.searchById(id);
        return response;
    }

    @GetMapping("/filter/{name}")
    public ResponseEntity<ProductResponseRest> searchByName(@PathVariable String name){
        ResponseEntity<ProductResponseRest> response = productService.searchByName(name);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponseRest> deleteById(@PathVariable Long id){
        ResponseEntity<ProductResponseRest> response = productService.deleteById(id);
        return response;
    }

    @GetMapping
    public ResponseEntity<ProductResponseRest> search(){
        ResponseEntity<ProductResponseRest> response = productService.search();
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseRest> update(
            @PathVariable Long id,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("picture")MultipartFile picture,
            @RequestParam("name")String name,
            @RequestParam("price")int price,
            @RequestParam("stock")int stock) throws IOException
    {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        product.setPicture(Util.compressZLib(picture.getBytes()));

        ResponseEntity<ProductResponseRest> response = productService.update(id, categoryId, product);

        return response;
    }

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=productos.xlsx";
        response.setHeader(headerKey, headerValue);

        ResponseEntity<ProductResponseRest> productsResponse = productService.search();
        ProductExcelExport excelExporter = new ProductExcelExport(productsResponse.getBody().getProduct().getProducts());
        excelExporter.export(response);

    }
}
