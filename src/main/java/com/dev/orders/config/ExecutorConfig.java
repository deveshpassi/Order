package com.dev.orders.config;

import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ExecutorConfig {
      @Bean(name = "paymentExecutor")
      public ThreadPoolExecutor paymentExecutor() {
            int corePoolSize = 4;
            int maxPoolSize = 8;
            int queueCapacity = 50;

            return new ThreadPoolExecutor(
                      corePoolSize,
                      maxPoolSize,
                      60L,
                      TimeUnit.SECONDS,
                      new ArrayBlockingQueue<>(queueCapacity),
                      new ThreadPoolExecutor.AbortPolicy()
            ){
                  @Override
                  public void execute(Runnable command) {
                        var context = MDC.getCopyOfContextMap();
                        super.execute(()->{
                              if (context != null) {
                                    MDC.setContextMap(context);
                              }
                              try{
                                    command.run();
                              }finally{
                                    MDC.clear();
                              }
                        });
                  }
            };
      }
}
