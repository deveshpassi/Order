package com.dev.orders.services;

import com.dev.orders.model.Order;
import com.dev.orders.model.OrderStatus;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class PaymentService {

      private final ThreadPoolExecutor executor;
      private final Random random = new Random();
      private static final Logger LOG = LoggerFactory.getLogger(PaymentService.class);

      public PaymentService(ThreadPoolExecutor Paymentexecutor) {
            this.executor = Paymentexecutor;
      }
      public void processPaymentAsync(Order order){
            try {
                  executor.execute(() -> {
                        try {
                              LOG.info("Starting payment processing for order {}", order.getId());
                              order.setStatus(OrderStatus.PROCESSING);
                              Thread.sleep(100 + random.nextInt(500));

                              if (random.nextInt(100) == 50) {
                                    order.setStatus(OrderStatus.FAILED);
                              } else {
                                    order.setStatus(OrderStatus.COMPLETED);
                              }
                        } catch (InterruptedException e) {
                              Thread.currentThread().interrupt();
                              order.setStatus(OrderStatus.FAILED);
                              LOG.error("Payment interrupted for order{}", order.getId(), e);
                        }
                  });
            }catch(RejectedExecutionException e){
                  order.setStatus(OrderStatus.FAILED);
                  LOG.error("Payment rejected due to oveloading for order {}", order.getId());

                  throw e;
            }
      }
}