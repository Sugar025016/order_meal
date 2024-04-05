package com.order_meal.order_meal.model.response;

import org.springframework.beans.BeanUtils;

import com.order_meal.order_meal.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryResponse {


    
    private Integer id;

    private String name;

    public CategoryResponse(Category category) {

        BeanUtils.copyProperties(category,this);

    }



}