package com.order_meal.order_meal.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.order_meal.order_meal.entity.Product;
import com.order_meal.order_meal.model.request.BackstageProductAddRequest;
import com.order_meal.order_meal.model.request.BackstageProductPutRequest;
import com.order_meal.order_meal.model.request.SellProductRequest;
import com.order_meal.order_meal.model.response.BackstageProductResponse;

public interface IProductService {
    List<Product> findAll();

    Product getProductById(int id);

    List<Product> getProductsByShopId(int shopId);

    // Product deleteProductById(int id) throws NotFoundException;

    // Product putProductById(int id);
    Page<BackstageProductResponse> getByPage(Integer shopId, Pageable pageable);

    boolean putShop(BackstageProductPutRequest productPutRequest);

    boolean addProduct(BackstageProductAddRequest productAddRequest);

    boolean deleteProductById(int productId, int userId);

    boolean setOrderable(int productId, boolean isOrderable, int userId);


    List<Product> getProductsByShopIdAndUserID(int productId, int userId);

    boolean putSellProduct(SellProductRequest sellProductRequest,int productId,int userId) ;

    boolean addSellProduct(SellProductRequest sellProductRequest,int shopId, int userId) ;
}
