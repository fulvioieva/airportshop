package com.e_commerceWebApp.services;

import com.e_commerceWebApp.entity.Order;

import java.util.List;

public interface IOrderService {


    List<Order> getAllOrders();

    public Order getOrderById(Integer id);

    public Order saveOrder(Order order);

    public boolean deleteOrder(Order order);

    int updateOrder(Order order);

    boolean existsById(Integer orderId);
}
