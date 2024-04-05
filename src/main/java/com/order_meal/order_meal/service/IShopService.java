package com.order_meal.order_meal.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.order_meal.order_meal.entity.Shop;
import com.order_meal.order_meal.entity.User;
import com.order_meal.order_meal.model.request.BackstageShopAddRequest;
import com.order_meal.order_meal.model.request.BackstageShopPutRequest;
import com.order_meal.order_meal.model.request.ShopRequest;
import com.order_meal.order_meal.model.request.ShopSearchRequest;
import com.order_meal.order_meal.model.response.BackstageShopResponse;
import com.order_meal.order_meal.model.response.ShopResponse;

public interface IShopService {
    // List<Shop> getShops();
    // Set<Shop> findShops(ShopSearchRequest shopRequest);
    Page<ShopResponse> findShops(ShopSearchRequest shopRequest , Pageable pageable);
    Page<ShopResponse> findShops( User user,ShopSearchRequest shopRequest , Pageable pageable);
    Shop getShopById(int id);
    Page<BackstageShopResponse> findShopsForAdmin(ShopSearchRequest shopRequest, Pageable pageable);
    Shop addShop(ShopRequest shopRequest, User user);
    // boolean existsById(int id);
    boolean putShop(BackstageShopPutRequest shopPutRequest);
    boolean addShop(BackstageShopAddRequest ShopAddRequest );
    Set<Shop> findShopsLim();
    List<Shop> findShopsByName(String name);
    List<Shop> getShopsByUserId(int id);
    Shop getShop(int shopId,int userId);
    boolean deleteShop(int id);
    boolean existsByName(String name);
}
