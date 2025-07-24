package com.shopping.inventory_service.controller;

import com.shopping.inventory_service.dto.InventoryResponseDto;
import com.shopping.inventory_service.service.serviceImpl.InventoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {


    private final InventoryServiceImpl inventoryService;
    @Autowired
    public InventoryController(InventoryServiceImpl inventoryService) {
        this.inventoryService = inventoryService;
    }

    //    example URI=http://localhost:8082/api/v1/inventory/status?skuCode=iphone_13,iphone_14
    @GetMapping("/status")
    public ResponseEntity<List<InventoryResponseDto>> isIntStock(@RequestParam List<String> skuCode){
        List<InventoryResponseDto> stockDetails = inventoryService.isInStock(skuCode);
        return ResponseEntity.ok(stockDetails);
    }

}
