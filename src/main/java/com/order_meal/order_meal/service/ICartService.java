package com.order_meal.order_meal.service;

import com.order_meal.order_meal.entity.Cart;
import com.order_meal.order_meal.entity.User;
import com.order_meal.order_meal.model.request.CartRequest;
import com.order_meal.order_meal.model.response.ShopCartResponse;

public interface ICartService {

    ShopCartResponse getAllByUserId(int userId);

    int addCart(int userId,CartRequest cartRequest);


    ShopCartResponse putCart(int userId, int cartId,CartRequest cartRequest);

    ShopCartResponse putCart(int userId, int cartId,int qty);

    ShopCartResponse deleteCart(int userId,int cartId);

    Cart getCartByUserId(int id);
    
    User deleteAllCart( User user) ;
}
