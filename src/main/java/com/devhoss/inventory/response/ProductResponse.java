package com.devhoss.inventory.response;

import com.devhoss.inventory.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {

    List<Product> products;

}
