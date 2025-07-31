package com.programmingtechie.orderservice.events;

public class OrderPlacedEvent {
    public OrderPlacedEvent(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    private String orderNumber;

}
