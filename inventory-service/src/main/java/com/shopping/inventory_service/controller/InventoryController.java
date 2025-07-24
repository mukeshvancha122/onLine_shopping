package com.shopping.inventory_service.controller;

import com.shopping.inventory_service.service.serviceImpl.InventoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {


    private final InventoryServiceImpl inventoryService;
    @Autowired
    public InventoryController(InventoryServiceImpl inventoryService) {
        this.inventoryService = inventoryService;
    }
    @GetMapping("/status/{skuCode}")
    public ResponseEntity<Boolean> isIntStock(@PathVariable String skuCode){
        Boolean isInStock = inventoryService.isInStock(skuCode);
        return ResponseEntity.ok(isInStock);
    }

}
