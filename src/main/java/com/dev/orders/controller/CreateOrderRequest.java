package com.dev.orders.controller;

import java.util.List;

public class CreateOrderRequest {

      private Long userId;
      private List<String> items;
      private double amount;

      public Long getUserId() {
            return userId;
      }

      public List<String> getItems() {
            return items;
      }

      public double getAmount() {
            return amount;
      }
}
