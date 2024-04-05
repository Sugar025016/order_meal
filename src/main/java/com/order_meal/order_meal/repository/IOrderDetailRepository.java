package com.order_meal.order_meal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// @Repository
// public class IOrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
import org.springframework.stereotype.Repository;

import com.order_meal.order_meal.entity.OrderDetail;
    
// }
@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

	
}