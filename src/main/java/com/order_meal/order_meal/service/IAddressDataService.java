package com.order_meal.order_meal.service;

import com.order_meal.order_meal.entity.AddressData;
import com.order_meal.order_meal.model.request.AddressRequest;

public interface IAddressDataService {
    AddressData getAddressData( int addressId);
    AddressData getAddressData( AddressRequest addressStreet);
}

