package com.example.ecommerce.orderservice.controller;

import com.example.ecommerce.orderservice.models.Order;
import com.example.ecommerce.orderservice.pojos.OrderRequest;
import com.example.ecommerce.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/create",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String createOrder(@RequestBody OrderRequest orderRequest) {
        Order order = new Order();
        order.setItemName(orderRequest.getItemName());
        order.setItemQuantity(orderRequest.getItemQuantity());
        orderService.saveOrder(order);
        return "hello world";
    }

    @GetMapping(value = "/get/{orderId}")
    public @ResponseBody String getOrder(@PathVariable String orderId) {
        List<Order> allOrders = orderService.getAllOrders();
        return "hello world";
    }
}
