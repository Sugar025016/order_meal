package com.order_meal.order_meal.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.order_meal.order_meal.model.request.OrderRequest;
import com.order_meal.order_meal.model.response.OrderFinishResponse;
import com.order_meal.order_meal.model.response.OrderResponse;

public interface IOrderService {

    boolean addOrder(int userId, OrderRequest orderRequest);

    Page<OrderResponse> getOrder(int userId, int OrderCategory, Pageable pageable);

    List<OrderResponse> getOrder(int userId, int OrderCategory);

    Page<OrderFinishResponse> getOrderByShop(int userId, int shopId, List<Integer> keyByClassify, Pageable pageable);

    boolean putOrderByShop(int userId, int shopId, int status, List<Integer> keyByClassify);

    List<OrderResponse> getNewOrderByUser(int userId);

    boolean putOrderStatus(int userId, int statusKey, List<Integer> orderIds);
}
