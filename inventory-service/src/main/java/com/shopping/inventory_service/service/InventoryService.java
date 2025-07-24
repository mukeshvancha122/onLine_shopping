package com.shopping.inventory_service.service;

import com.shopping.inventory_service.dto.InventoryResponseDto;

import java.util.List;

public interface InventoryService {
    public List<InventoryResponseDto> isInStock(List<String> skuCode);
}

