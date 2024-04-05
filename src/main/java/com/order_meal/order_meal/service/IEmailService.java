package com.order_meal.order_meal.service;

public interface IEmailService {

    public void sendSimpleMessage(
            String to, String subject, String text);
}
