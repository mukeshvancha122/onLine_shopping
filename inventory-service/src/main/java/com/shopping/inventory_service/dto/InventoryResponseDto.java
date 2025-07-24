package com.shopping.inventory_service.dto;

public class InventoryResponseDto {


    private String skuCode;
    private boolean isInStock;

    public boolean isInStock() {
        return isInStock;
    }

    public void setInStock(boolean inStock) {
        isInStock = inStock;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }




}
