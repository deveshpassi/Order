package com.dev.orders.repository;

import com.dev.orders.model.Order;
import org.springframework.stereotype.Repository;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class OrderRepository {
      private final ConcurrentHashMap<Long, Order> storage = new ConcurrentHashMap<>();
      private final AtomicLong idGenerator = new AtomicLong(1);

      public Order save(Order order) {
            storage.put(order.getId(),order);
            return order;
      }
      public Long nextId(){
            return idGenerator.getAndIncrement();
      }
      public Order findById(Long id){
            return storage.get(id);
      }
}
