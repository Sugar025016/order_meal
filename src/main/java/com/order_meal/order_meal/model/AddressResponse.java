package com.order_meal.order_meal.model;

import org.springframework.beans.BeanUtils;

import com.order_meal.order_meal.entity.AddressData;

import lombok.Data;

@Data
public class AddressResponse {
    private Integer id;

    private String city;

    private String area;
    private String street;

    private String detail;

    private double lat;

    private double lng;

    public AddressResponse() {

    }

    public AddressResponse(com.order_meal.order_meal.entity.Address address) {
        AddressData addressData = address.getAddressData();
        BeanUtils.copyProperties(address, this);
        this.city = addressData.getCity();
        this.area = addressData.getArea();
        this.street = addressData.getStreet();
    }

    public AddressResponse(com.order_meal.order_meal.entity.ShopAddress shopAddress) {
        AddressData addressData = shopAddress.getAddressData();
        BeanUtils.copyProperties(shopAddress, this);
        this.city = addressData.getCity();
        this.area = addressData.getArea();
        this.street = addressData.getStreet();
    }
}
