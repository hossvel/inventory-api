package com.devhoss.inventory.response;

import com.devhoss.inventory.model.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {

    private List<Category> category;

}
