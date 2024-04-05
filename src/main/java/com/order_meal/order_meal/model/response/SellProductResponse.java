package com.order_meal.order_meal.model.response;

import org.springframework.beans.BeanUtils;

import com.order_meal.order_meal.entity.Product;

import lombok.Data;


@Data
public class SellProductResponse {

    private Integer id;
    // @JsonProperty("productName")
    private String name;
    private String description;
    private Integer prise;

    private boolean isOrderable;

    private String imgUrl;
    private Integer imgId;

    public SellProductResponse(Product product) {
        BeanUtils.copyProperties(product, this);
        if (product.getFileData() != null) {
            this.imgUrl = product.getFileData().getFileName();
            this.imgId = product.getFileData().getId();
        }
    }
}
