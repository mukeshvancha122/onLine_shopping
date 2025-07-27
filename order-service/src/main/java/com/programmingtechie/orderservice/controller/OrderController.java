package com.programmingtechie.orderservice.controller;

import com.programmingtechie.orderservice.dto.OrderRequest;
import com.programmingtechie.orderservice.model.Order;
import com.programmingtechie.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name="inventoryService",fallbackMethod = "fallbackPlaceOrder")
    @TimeLimiter(name = "inventoryService")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        return CompletableFuture.supplyAsync(()->"Order created successfully!");
    }
    public CompletableFuture<String> fallbackPlaceOrder(OrderRequest orderRequest, RuntimeException runtimeException) {
        log.info("Exception : "+ runtimeException+"Order creation failed due to inventory service issue"+orderRequest);
        return CompletableFuture.supplyAsync(() -> "Order creation failed, please try again later.");
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Order>> getAllOrders() {
        log.info("Fetching all orders");
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.getAllOrders());
    }

}
