package com.dev.orders.controller;

import com.dev.orders.model.Order;
import com.dev.orders.services.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

      private final OrderService orderService;

      public OrderController(OrderService orderService){
            this.orderService = orderService;
      }

      @PostMapping
      public Order create(@RequestBody CreateOrderRequest request){
            return orderService.createOrder(
                      request.getUserId(),
                      request.getItems(),
                      request.getAmount()
            );
      }
      @GetMapping("/{id}")
      public Order get(@PathVariable Long id){
            return orderService.getOrderById(id);
      }

}
