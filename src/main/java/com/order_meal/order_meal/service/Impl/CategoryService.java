package com.order_meal.order_meal.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order_meal.order_meal.entity.Category;
import com.order_meal.order_meal.repository.ICategoryRepository;
import com.order_meal.order_meal.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    ICategoryRepository iCategoryRepository;

    @Override
    public List<Category> findAll() {
        return iCategoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(int id) {
        Optional<Category> findById = iCategoryRepository.findById(id);
        if(!findById.isPresent()){
            return null;
        }
        return  findById.get();
    }

    @Override
    public boolean deleteById(int id) {
            iCategoryRepository.deleteById(id);
            return !existsById(id);
    }
    @Override
    public boolean existsById(int id) {
        return iCategoryRepository.existsById(id);
    }

}
