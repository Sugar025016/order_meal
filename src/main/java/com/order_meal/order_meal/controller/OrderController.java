package com.order_meal.order_meal.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.order_meal.order_meal.config.CustomUserDetails;
import com.order_meal.order_meal.enums.OrderCategory;
import com.order_meal.order_meal.model.request.OrderRequest;
import com.order_meal.order_meal.model.response.OrderResponse;
import com.order_meal.order_meal.service.Impl.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    // @Value("${imageGetUrl}")
    // String imageGetUrl;

    @Autowired
    OrderService orderService;

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<Boolean> getUser(@AuthenticationPrincipal CustomUserDetails customUserDetails,
            @Valid @RequestBody OrderRequest takeTime) {
        return ResponseEntity.ok().body(orderService.addOrder(customUserDetails.getId(), takeTime));
    }


    @RequestMapping(path = "/{page}", method = RequestMethod.GET)
    public ResponseEntity<Page<OrderResponse>> getOrderByUserPage(@AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable int page) {

        Pageable pageable = PageRequest.of(page-1, 10);
        
        return ResponseEntity.ok().body(orderService.getOrder(customUserDetails.getId(),OrderCategory.END.getValue(), pageable));
    }


    // @RequestMapping(path = "/", method = RequestMethod.GET)
    // public ResponseEntity<List<OrderResponse>> getOrderByUser(@AuthenticationPrincipal CustomUserDetails customUserDetails) {

        
    //     return ResponseEntity.ok().body(orderService.getOrder(customUserDetails.getId(),OrderCategory.ONGOING.getValue()));
    // }

    

    // @RequestMapping(path = "/{page}", method = RequestMethod.GET)
    // public ResponseEntity<Page<OrderResponse>> getOrderByUser(@AuthenticationPrincipal CustomUserDetails customUserDetails,
    //         @PathVariable int page) {

    //     Pageable pageable = PageRequest.of(page-1, 10);
        
    //     return ResponseEntity.ok().body(orderService.getOrder(customUserDetails.getId(), pageable));
    // }


    // @RequestMapping(path = "/shop/{shopId}", method = RequestMethod.GET)
    // public ResponseEntity<Page<OrderResponse>> getOrderByShop(@AuthenticationPrincipal CustomUserDetails customUserDetails,
    //         @PathVariable int shopId) {

    //     Pageable pageable = PageRequest.of(page-1, 10);
        
    //     return ResponseEntity.ok().body(orderService.getOrder(customUserDetails.getId(), pageable)); 
    // }

        
    // @RequestMapping(path = "/{page}", method = RequestMethod.GET)
    // public ResponseEntity<Page<OrderResponse>> getOrderByShop(@AuthenticationPrincipal CustomUserDetails customUserDetails,
    //         @PathVariable int page) {

    //     Pageable pageable = PageRequest.of(page-1, 10);
        
    //     return ResponseEntity.ok().body(orderService.getOrder(customUserDetails.getId(), pageable));
    // }
}
