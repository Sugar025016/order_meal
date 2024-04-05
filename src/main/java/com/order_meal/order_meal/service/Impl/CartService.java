package com.order_meal.order_meal.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.order_meal.order_meal.entity.Cart;
import com.order_meal.order_meal.entity.Product;
import com.order_meal.order_meal.entity.User;
import com.order_meal.order_meal.model.request.CartRequest;
import com.order_meal.order_meal.model.response.ShopCartResponse;
import com.order_meal.order_meal.repository.ICartRepository;
import com.order_meal.order_meal.repository.IProductRepository;
import com.order_meal.order_meal.repository.IUserRepository;
import com.order_meal.order_meal.service.ICartService;

@Service
public class CartService implements ICartService {

    @Autowired
    ICartRepository iCartRepository;
    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    IProductRepository iProductRepository;

    @Override
    public Cart getCartByUserId(int id) {
        Cart cart = iCartRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found"));

        return cart;
    }

    @Override
    public ShopCartResponse getAllByUserId(int id) {
        List<Cart> allByUser_id = iCartRepository.getAllByUser_id(id);
        ShopCartResponse shopCartResponse = new ShopCartResponse(allByUser_id);
        return shopCartResponse;
    }

    @Transactional
    @Override
    public int addCart(int id, CartRequest cartRequest) {

        User userOptional = iUserRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Product product = iProductRepository.findById(cartRequest.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        this.getClass().getName() + "Product not found"));

        List<Cart> carts = iCartRepository.getAllByUser_id(id);

        boolean anyMatch = carts.stream().anyMatch(v -> v.getProduct().getShop().getId() != product.getShop().getId());
        if (anyMatch) {
            iCartRepository.deleteAll(carts);
            iCartRepository.flush();
        }

        Cart cart = new Cart(cartRequest, userOptional, product);

        iCartRepository.save(cart);

        return iCartRepository.getAllByUser_id(id).stream().mapToInt(v -> v.getQty()).sum();

    }

    @Override
    public ShopCartResponse putCart(int userId, int cartId, CartRequest cartRequest) {

        Optional<Cart> cart = iCartRepository.findByIdAndUser_id(cartId, userId);

        Cart orElseThrow = cart.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        orElseThrow.setCart(cartRequest);

        iCartRepository.save(orElseThrow);

        return getAllByUserId(userId);
    }

    @Override
    public ShopCartResponse deleteCart(int cartId, int userId) {
        Optional<Cart> cartOptional = iCartRepository.getByIdAndUser_id(cartId, userId);

        Cart cart = cartOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        iCartRepository.delete(cart);

        return getAllByUserId(userId);

    }

    @Override
    public User deleteAllCart( User user) {

        int deletedCount =iCartRepository.deleteAllByUserId(user.getId());
        if(deletedCount>0){
            return user;
        }else{
            throw new RuntimeException("Failed to delete carts for user: " + user.getId());
        }

        

    }

    @Override
    public ShopCartResponse putCart(int userId, int cartId, int qty) {

        Optional<Cart> cart = iCartRepository.findByIdAndUser_id(cartId, userId);

        Cart orElseThrow = cart.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        orElseThrow.setQty(qty);
        iCartRepository.save(orElseThrow);

        return getAllByUserId(userId);

    }

}
