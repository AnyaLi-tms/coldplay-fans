package com.oocl.coldplayfans.repository;

import com.oocl.coldplayfans.dao.Order;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDbRepository implements OrderRepository {

    @Autowired
    private JpaOrderRepository jpaOrderRepository;

    public OrderDbRepository(JpaOrderRepository jpaOrderRepository) {
        this.jpaOrderRepository = jpaOrderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return jpaOrderRepository.findAll();
    }

    @Override
    public Order getOrderById(Integer id) {
        return jpaOrderRepository.findById(id).orElse(null);
    }

    @Override
    public Order createOrder(Order order) {
        return jpaOrderRepository.save(order);
    }

    @Override
    public Order updateOrder(Integer id, Order order) {
        return jpaOrderRepository.findById(id).map(existingOrder -> {
            existingOrder.setStatus(order.getStatus());
            existingOrder.setIsDeleted(order.getIsDeleted());
            existingOrder.setAddress(order.getAddress());
            return jpaOrderRepository.save(existingOrder);
        }).orElse(null);
    }

    @Override
    public void deleteOrder(Integer id) {
        jpaOrderRepository.deleteById(id);
    }

    @Override
    public List<Order> getOrdersByItemType(String itemType, Integer userId) {
        return jpaOrderRepository.findByItemType(itemType, userId);
    }
    
    
} 