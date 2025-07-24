package com.shopping.product_service.service;

import com.shopping.product_service.dto.ProductRequestDTO;
import com.shopping.product_service.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {
   public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);
    public List<ProductResponseDTO> getAllProducts();
    public ProductResponseDTO getProductById(String productId);

}
