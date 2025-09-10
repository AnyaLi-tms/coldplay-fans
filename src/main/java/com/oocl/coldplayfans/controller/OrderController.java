package com.oocl.coldplayfans.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oocl.coldplayfans.dao.Order;
import com.oocl.coldplayfans.service.OrderService;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order getOrderById(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/type/{itemType}")
    public List<Order> getOrdersByItemType(@PathVariable String itemType) {
        return orderService.getOrdersByItemType(itemType);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order updateOrder(@PathVariable Integer id, @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }

}
