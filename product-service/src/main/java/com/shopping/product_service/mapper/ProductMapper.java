package com.shopping.product_service.mapper;

import com.shopping.product_service.dto.ProductRequestDTO;
import com.shopping.product_service.dto.ProductResponseDTO;
import com.shopping.product_service.model.Product;

public class ProductMapper {

    public static Product toEntity(ProductRequestDTO productRequestDTO){
        Product product = new Product();
        product.setProductName(productRequestDTO.getProductName());
        product.setProductDescription(productRequestDTO.getProductDescription());
        product.setProductPrice(productRequestDTO.getProductPrice());
        return product;
    }

    public static ProductResponseDTO toResponseDTO(Product product){
        ProductResponseDTO productResponseDTO=new ProductResponseDTO();
        productResponseDTO.setProductId(product.getProductId());
        productResponseDTO.setProductName(product.getProductName());
        productResponseDTO.setProductDescription(product.getProductDescription());
        productResponseDTO.setProductPrice(product.getProductPrice());
        return productResponseDTO;
    }
}
