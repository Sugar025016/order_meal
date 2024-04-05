package com.order_meal.order_meal.service;

import java.util.Set;

import com.order_meal.order_meal.entity.Tab;
import com.order_meal.order_meal.model.request.TabProductRequest;

public interface ITabService {
    Set<Tab> findTabByShopId(int id);

    boolean setTabByShopId(int tabId, TabProductRequest tabProductRequest, int userId);

    boolean addTabByShopId( TabProductRequest tabProductRequest, int userId);
    boolean deleteTab(int tabId, int userId) ;
}
