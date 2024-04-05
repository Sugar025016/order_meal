package com.order_meal.order_meal.service;

import com.order_meal.order_meal.model.request.SchedulesRequest;

public interface ISchedulesService {


    boolean putSchedule(int userId, int shopId, SchedulesRequest schedulesRequest);


    
}
