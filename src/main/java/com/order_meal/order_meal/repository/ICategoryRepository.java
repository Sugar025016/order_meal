package com.order_meal.order_meal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.order_meal.order_meal.entity.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category,Integer> {

    Category getCategoryById(Integer id);

    // Category getCategoryById(Integer id);
    // @Modifying
    // int  deleteByCategory(Category category);

    @Modifying
    void  deleteById(Integer id);
}
