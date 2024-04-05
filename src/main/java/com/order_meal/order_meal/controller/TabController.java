package com.order_meal.order_meal.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.order_meal.order_meal.config.CustomUserDetails;
import com.order_meal.order_meal.entity.Tab;
import com.order_meal.order_meal.model.request.TabProductRequest;
import com.order_meal.order_meal.model.response.TabProductResponse;
import com.order_meal.order_meal.service.Impl.TabService;

import lombok.NonNull;

@Validated
@RestController
@RequestMapping("/api/tab")
public class TabController {

    @Autowired
    TabService tabService;

    @RequestMapping(path = "/{shopId}", method = RequestMethod.GET)
    public ResponseEntity<List<TabProductResponse>> getTabProducts(@PathVariable int shopId) {
        Set<Tab> findTabByShopId = tabService.findTabByShopId(shopId);

        List<TabProductResponse> collect = findTabByShopId.stream().map(v -> new TabProductResponse(v,shopId))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(collect);
    }

    @RequestMapping(path = "/{tabId}", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> putTabProducts(@NonNull @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable int tabId, @Valid @RequestBody TabProductRequest tabProductRequest) {

        return ResponseEntity.ok().body(tabService.setTabByShopId(tabId, tabProductRequest, customUserDetails.getId()));
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<Boolean> putTabProducts(@AuthenticationPrincipal CustomUserDetails customUserDetails,
           @Valid @RequestBody TabProductRequest tabProductRequest) {
        return ResponseEntity.ok().body(tabService.addTabByShopId(tabProductRequest, customUserDetails.getId()));
    }

    @RequestMapping(path = "/{tabId}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteTabProducts(
            @NonNull @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable int tabId) {
                

        return ResponseEntity.ok().body(tabService.deleteTab(tabId,  customUserDetails.getId()));
    }
}
