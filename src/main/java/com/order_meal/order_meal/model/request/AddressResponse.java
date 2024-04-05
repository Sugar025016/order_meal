package com.order_meal.order_meal.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AddressResponse {

    private Integer id;

    private String city;

    private String area;

    private String detail;

}