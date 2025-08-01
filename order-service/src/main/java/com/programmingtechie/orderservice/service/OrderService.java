package com.programmingtechie.orderservice.service;

import com.programmingtechie.orderservice.config.WebClientConfig;
import com.programmingtechie.orderservice.dto.InventoryResponseDto;
import com.programmingtechie.orderservice.dto.OrderLineItemsDto;
import com.programmingtechie.orderservice.dto.OrderRequest;
import com.programmingtechie.orderservice.events.OrderPlacedEvent;
import com.programmingtechie.orderservice.model.Order;
import com.programmingtechie.orderservice.model.OrderLineItems;
import com.programmingtechie.orderservice.repository.OrderRepository;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
    @Autowired
    public OrderService(OrderRepository orderRepository, WebClient webClient,KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.webClient = webClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        System.out.println("Order Line Items: " + orderLineItems);

        order.setOrderLineItemsList(orderLineItems);
        List<String> skucodes= orderLineItems.stream().map(OrderLineItems::getSkuCode).collect(Collectors.toList());
        log.info("Order Line Items SKU Codes: {}", skucodes);
//        calling the inventory service to check if the items are in stock
        InventoryResponseDto[] inventoryResponseDtoList= webClient.get()
                .uri("http://localhost:8003/api/v1/inventory/status", uriBuilder -> uriBuilder.queryParam("skuCode", skucodes).build())
                .headers(headers->log.info("OutGoing headers:{}",headers))
                .retrieve()
                .bodyToMono(InventoryResponseDto[].class).block();

        assert inventoryResponseDtoList != null;
        boolean productsInStockArray=Arrays.stream(inventoryResponseDtoList).allMatch(InventoryResponseDto::isInStock);
        if(productsInStockArray){
            orderRepository.save(order);
            kafkaTemplate.send("notificationTopic",new OrderPlacedEvent(order.getOrderNumber()));
        }
        else {
            log.error("Products are not in stock, cannot place order");
        }


    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
