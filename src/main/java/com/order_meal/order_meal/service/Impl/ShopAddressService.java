package com.order_meal.order_meal.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.order_meal.order_meal.entity.AddressData;
import com.order_meal.order_meal.entity.ShopAddress;
import com.order_meal.order_meal.model.request.AddressRequest;
import com.order_meal.order_meal.repository.IShopAddressRepository;
import com.order_meal.order_meal.service.IShopAddressService;
import com.order_meal.order_meal.utils.GeocodingService;
import com.order_meal.order_meal.utils.GeocodingService.Coordinates;

@Service
public class ShopAddressService implements IShopAddressService {

    @Autowired
    GeocodingService geocodingService;

    @Autowired
    AddressDataService addressDataService;

    @Autowired
    IShopAddressRepository iSopAddressRepository;

    // @Autowired
    // UserService userService;



    @Override
    public void geocodeAddress(String address) {
        System.out.println(address);
        geocodingService.geocodeAddress(address);

    }

    @Override
    @Transactional
    public ShopAddress addAddress(AddressData addressData, String detail) {

        String addressStr = addressData.getCity() + addressData.getArea() + addressData.getStreet() + detail;

        Coordinates geocodeAddress = geocodingService.geocodeAddress(addressStr);

        ShopAddress address = new ShopAddress(addressData, detail, geocodeAddress.getLat(), geocodeAddress.getLng());

        ShopAddress save = iSopAddressRepository.save(address);

        return save;

    }



    @Override
    @Transactional
    public ShopAddress putShopAddress(ShopAddress shopAddress, AddressRequest addressRequest) {
        AddressData addressData = addressDataService.getAddressData(addressRequest);

        String addressStr = addressData.getCity() + addressData.getArea() + addressData.getStreet() + addressRequest.getDetail();

        Coordinates geocodeAddress = geocodingService.geocodeAddress(addressStr);
        shopAddress.setAddressData(addressData);
        shopAddress.setDetail(addressRequest.getDetail());
        shopAddress.setLat(geocodeAddress.getLat());
        shopAddress.setLng( geocodeAddress.getLng());

        ShopAddress save = iSopAddressRepository.save(shopAddress);

        return save;
    }



}
