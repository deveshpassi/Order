package com.dev.orders.services;

import com.dev.orders.repository.OrderRepository;
import com.dev.orders.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

      private final OrderRepository orderRepository;
      private final PaymentService paymentService;

      public OrderService(OrderRepository orderRepository, PaymentService paymentService) {
          this.orderRepository = orderRepository;
          this.paymentService = paymentService;
      }
      public Order createOrder(Long userId, List<String> items, double amount){
            Long id = orderRepository.nextId();
            Order order = new Order(id, userId, items, amount);
            orderRepository.save(order);

            paymentService.processPaymentAsync(order);

            return order;
      }
      public Order getOrderById(Long id){
            return orderRepository.findById(id);
      }
}