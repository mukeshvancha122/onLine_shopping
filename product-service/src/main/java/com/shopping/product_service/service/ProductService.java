package com.shopping.product_service.service;

import com.shopping.product_service.dto.ProductRequestDTO;
import com.shopping.product_service.dto.ProductResponseDTO;

public interface ProductService {
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);
}
