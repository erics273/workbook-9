package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Product;

import java.util.List;

public interface ProductDao {

    Product add(Product product);

    List<Product> getAll();

    Product findById(int id);

   // void deleteById(int id);

   // void updateById(int id);

}
