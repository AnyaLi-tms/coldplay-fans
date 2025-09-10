package com.oocl.coldplayfans.repository;

import java.util.List;

import com.oocl.coldplayfans.dao.Order;


public interface OrderRepository {
    List<Order> getAllOrders();
    Order getOrderById(Integer id);
    Order createOrder(Order order);
    Order updateOrder(Integer id, Order order);
    void deleteOrder(Integer id);
    List<Order> getOrdersByItemType(String itemType, Integer userId);
}
