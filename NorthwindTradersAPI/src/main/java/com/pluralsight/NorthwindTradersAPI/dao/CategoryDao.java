package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Category;
import com.pluralsight.NorthwindTradersAPI.models.Product;

import java.util.List;

public interface CategoryDao {

    Category add(Category category);

    List<Category> getAll();

    Category findById(int id);

    void deleteById(int id);

    void updateById(int id, Category category);

}
