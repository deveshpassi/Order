package com.dev.orders.model;

import java.time.Instant;
import java.util.List;

public class Order {

      private Long id;
      private Long userId;
      private List<String> items;
      private double amount;
      private OrderStatus status;
      private Instant createdAt;

      public Order(Long id, Long userId, List<String> items, double amount) {
            this.id = id;
            this.userId = userId;
            this.items = items;
            this.amount = amount;
            this.status = OrderStatus.PENDING;
            this.createdAt = Instant.now();
      }

      public Long getId() {
            return id;
      }

      public void setId(Long id) {
            this.id = id;
      }

      public Long getUserId() {
            return userId;
      }

      public void setUserId(Long userId) {
            this.userId = userId;
      }

      public List<String> getItems() {
            return items;
      }

      public void setItems(List<String> items) {
            this.items = items;
      }

      public double getAmount() {
            return amount;
      }

      public void setAmount(double amount) {
            this.amount = amount;
      }

      public OrderStatus getStatus() {
            return status;
      }

      public void setStatus(OrderStatus status) {
            this.status = status;
      }

      public Instant getCreatedAt() {
            return createdAt;
      }

      public void setCreatedAt(Instant createdAt) {
            this.createdAt = createdAt;
      }
}