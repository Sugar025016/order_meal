package com.order_meal.order_meal.model.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.order_meal.order_meal.entity.Order;
import com.order_meal.order_meal.entity.OrderDetail;
import com.order_meal.order_meal.entity.Shop;
import com.order_meal.order_meal.enums.OrderStatus;
import com.order_meal.order_meal.model.AddressResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderResponse {


    private Integer shopId;
    private Integer orderId;
    private String shopName;
    private String userName;
    private String description;
    private String imgUrl;
    private int totalPrise;
    private String remark;
    private String statusChinese;
    private int status;
    private LocalDateTime orderTime;
    private LocalDateTime takeTime;
    @JsonProperty("address")
    private AddressResponse address;
    private List<OrderDetailResponse> orderDetails;

    public OrderResponse(Order order) {
        BeanUtils.copyProperties(order,this);
        Shop shop = order.getShop();
        this.shopId=shop.getId();
        this.userName=order.getUser().getName();
        this.orderId=order.getId();
        this.shopName =shop.getName();
        this.description=shop.getDescription();
        List<OrderDetail> orderDetail = order.getOrderDetail();
        this.totalPrise = orderDetail.stream().mapToInt(v->v.getQty()*v.getPrise()).sum();
        this.orderTime=order.getCreateTime();
        this.status=  order.getStatus();
        this.statusChinese=  OrderStatus.getStatus(order.getStatus()).getChinese();
        this.address = new AddressResponse(order.getAddress());
        if(shop.getFileData()!=null){
            this.imgUrl=shop.getFileData().getFileName();
        }
        this.orderDetails = order.getOrderDetail().stream().map(OrderDetailResponse::new).collect(Collectors.toList());
    }

}
