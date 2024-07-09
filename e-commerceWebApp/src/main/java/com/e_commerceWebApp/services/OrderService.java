package com.e_commerceWebApp.services;

import com.e_commerceWebApp.entity.Order;
import com.e_commerceWebApp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService{
    @Autowired
    OrderRepository oR;

    @Override
    public List<Order> getAllOrders() {
        return oR.findAll();
    }

    @Override
    public Order getOrderById(Integer id) {
        return oR.findById(id).orElse(null);
    }

    @Override
    public Order saveOrder(Order order) {
        return oR.save(order);
    }

    @Override
    public boolean deleteOrder(Order order) {
        int id= order.getId();
        if (oR.existsById(id)){
            oR.delete(order);
            return true;
        }
        return false;
    }

    @Override
    public int updateOrder(Order order) {
        if (oR.existsById(order.getId())) {
            oR.save(order);
            return 1;
        }
        return 0;
    }

    @Override
    public boolean existsById(Integer orderId) {
        return oR.existsById(orderId);
    }
}
