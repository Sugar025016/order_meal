package com.order_meal.order_meal.model.response;

import com.order_meal.order_meal.entity.Shop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopNameItemResponse {

    private Integer id;

    private String name;

    public ShopNameItemResponse(Shop shop ) {
        this.id = shop.getId();
        this.name = shop.getName();
    }



    
}
