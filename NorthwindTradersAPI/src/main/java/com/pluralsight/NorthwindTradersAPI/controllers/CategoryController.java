package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.dao.CategoryDao;
import com.pluralsight.NorthwindTradersAPI.dao.ProductDao;
import com.pluralsight.NorthwindTradersAPI.models.Category;
import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;

    @GetMapping("/api/categories")
    public List<Category> getAll(){
        return categoryDao.getAll();
    }

    @GetMapping("/api/categories/{id}")
    public Category findById(@PathVariable int id){
        return categoryDao.findById(id);
    }

    @PostMapping("/api/categories")
    public Category add(@RequestBody Category category){
        return categoryDao.add(category);
    }

    @PutMapping("/api/categories/{id}")
    public void update(@PathVariable int id, @RequestBody Category category){
        categoryDao.updateById(id, category);
    }
}
