package com.shopping.inventory_service.service.serviceImpl;

import com.shopping.inventory_service.InventoryRepository;
import com.shopping.inventory_service.dto.InventoryResponseDto;
import com.shopping.inventory_service.mapper.InventoryMapper;
import com.shopping.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private List<InventoryResponseDto> inventoryResponseDto;

    @Override
    public List<InventoryResponseDto> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(InventoryMapper::toResponse).toList();





    }
}
