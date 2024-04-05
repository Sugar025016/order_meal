package com.order_meal.order_meal.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.order_meal.order_meal.entity.Address;
import com.order_meal.order_meal.entity.Shop;
import com.order_meal.order_meal.entity.User;
import com.order_meal.order_meal.model.request.PasswordRequest;
import com.order_meal.order_meal.model.request.UserPutRequest;
import com.order_meal.order_meal.model.request.UserRequest;
import com.order_meal.order_meal.model.response.BackstageUserResponse;

public interface IUserService {
    User findById(int id);
    User findByAccount(String account);
    Page<BackstageUserResponse> findByName(Pageable pageable, String name);
    boolean addUser(UserRequest userRequest);
    boolean existByAccount(String account);
    boolean putUser(UserPutRequest userPutRequest, int id);
    User putUserAddressDelivery(int userId , int addressId);

    boolean putUserPassword(PasswordRequest passwordRequest,int id);


    List<Shop> findLoveByAccount(int id );
    Boolean addOrDeleteUserLove(int id,int shopId) ;

    List<String> findByAccounts(String account);

    List<Address> putUserAddress(int userId,List<Address> addresses);
    User addMember(UserRequest userRequest);

    Address addUserAddress(int userId,Address address);

    boolean deleteAddressDelivery(int id, int addressId);
}
