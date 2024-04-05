package com.order_meal.order_meal.service;

import com.order_meal.order_meal.entity.AddressData;
import com.order_meal.order_meal.entity.ShopAddress;
import com.order_meal.order_meal.model.request.AddressRequest;

public interface IShopAddressService {
    public void geocodeAddress(String address);

    ShopAddress addAddress(AddressData addresses, String detail);

    ShopAddress putShopAddress(ShopAddress shopAddress, AddressRequest address);

}
