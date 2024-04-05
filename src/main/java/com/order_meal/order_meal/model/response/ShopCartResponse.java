package com.order_meal.order_meal.model.response;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.order_meal.order_meal.entity.Cart;
import com.order_meal.order_meal.entity.Product;
import com.order_meal.order_meal.entity.Shop;
import com.order_meal.order_meal.model.ScheduleWeek;
import com.order_meal.order_meal.model.ScheduleWeeks;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ShopCartResponse {

    private Integer shopId;

    private String shopName;

    private boolean isOrderable;

    private Double deliveryKm;

    private int deliveryPrice;

    private List<ScheduleWeek> schedules;

    private List<CartResponse> CartResponses;

    public ShopCartResponse(List<Cart> carts) {
        if (carts.size() != 0) {
            Shop shop = carts.get(0).getProduct().getShop();
            this.shopId = shop.getId();
            this.shopName = shop.getName();
            this.isOrderable = shop.isOrderable();
            this.deliveryKm = shop.getDeliveryKm();
            this.deliveryPrice = shop.getDeliveryPrice();
            this.CartResponses = carts.stream().map(v -> new CartResponse(v)).collect(Collectors.toList());
            this.schedules = new ScheduleWeeks(shop.getSchedulesForOpen()).getScheduleWeeks();
        }

    }

    @NoArgsConstructor
    @Getter
    @Setter
    public class CartResponse {

        @JsonProperty("cartId")
        private Integer id;

        private String department;

        private String orderUsername;

        private int qty;

        private String remark;

        private ProductResponse productResponse;

        public CartResponse(Cart cart) {

            BeanUtils.copyProperties(cart, this);
            this.productResponse = new ProductResponse(cart.getProduct());

        }

        @NoArgsConstructor
        @Getter
        @Setter
        public class ProductResponse {
            private Integer productId;
            private String productName;
            private double price;
            private boolean isOrderable;

            public ProductResponse(Product product) {
                this.productId = product.getId();
                this.productName = product.getName();
                this.price = product.getPrise();
                this.isOrderable = product.isOrderable();
            }

        }
    }

}
