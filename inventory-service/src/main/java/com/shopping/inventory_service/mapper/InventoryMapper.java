package com.shopping.inventory_service.mapper;

import com.shopping.inventory_service.dto.InventoryResponseDto;
import com.shopping.inventory_service.model.Inventory;

public class InventoryMapper {
    public static InventoryResponseDto toResponse(Inventory inventory) {
        InventoryResponseDto response = new InventoryResponseDto();
        response.setSkuCode(inventory.getSkuCode());
        response.setInStock(inventory.getQuantity() > 0);
        return response;
    }
}

