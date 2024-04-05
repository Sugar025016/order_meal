package com.order_meal.order_meal.service;

import java.util.List;

import com.order_meal.order_meal.entity.Category;

public interface ICategoryService {
    List<Category> findAll();    
    Category getCategoryById(int id);
    boolean deleteById(int id); 
    boolean existsById(int id) ;
}
