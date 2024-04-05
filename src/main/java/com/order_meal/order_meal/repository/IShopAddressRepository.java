package com.order_meal.order_meal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order_meal.order_meal.entity.ShopAddress;

public interface IShopAddressRepository extends JpaRepository<ShopAddress,Integer> {

    // Optional<Address> findByUserIdAndAddressId(int userId, Integer id);


    
}
