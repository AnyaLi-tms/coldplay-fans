package com.oocl.coldplayfans.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oocl.coldplayfans.dao.Order;
import com.oocl.coldplayfans.repository.OrderDbRepository;
import java.util.List;


@Service
public class OrderService {
    @Autowired
    private OrderDbRepository orderDbRepository;

    public OrderService(OrderDbRepository orderDbRepository) {
        this.orderDbRepository = orderDbRepository;
    }

    public List<Order> getAllOrders() {
        return orderDbRepository.getAllOrders();
    }

    public Order getOrderById(Integer id) {
        return orderDbRepository.getOrderById(id);
    }
    public Order createOrder(Order order) {
        return orderDbRepository.createOrder(order);
    }
    public void deleteOrder(Integer id) {
        orderDbRepository.deleteOrder(id);
    }
    public Order updateOrder(Integer id, Order updatedOrder) {
        return orderDbRepository.updateOrder(id, updatedOrder);
    }

    public List<Order> getOrdersByItemType(String itemType) {
        return orderDbRepository.getOrdersByItemType(itemType);
    }
}
