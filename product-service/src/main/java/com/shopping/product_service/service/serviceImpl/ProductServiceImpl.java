package com.shopping.product_service.service.serviceImpl;

import com.shopping.product_service.dto.ProductRequestDTO;
import com.shopping.product_service.dto.ProductResponseDTO;
import com.shopping.product_service.mapper.ProductMapper;
import com.shopping.product_service.model.Product;
import com.shopping.product_service.repository.ProductRepository;
import com.shopping.product_service.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO){
        Product product= ProductMapper.toEntity(productRequestDTO);
        product.setProductName(productRequestDTO.getProductName());
        product.setProductDescription(productRequestDTO.getProductDescription());
        product.setProductPrice(productRequestDTO.getProductPrice());
        product = productRepository.save(product);
//        log.info("Product with ID: {} saved is saved: {}", product.getProductId(), product);
        return ProductMapper.toResponseDTO(product);
    }

    public List<ProductResponseDTO> getAllProducts(){
        return productRepository.findAll().stream()
                .map(ProductMapper::toResponseDTO)
                .toList();
    }

    @Override
    public ProductResponseDTO getProductById(String  productId) {
        if(productId==null|| productId.isEmpty()){
            System.out.println("Product ID cannot be null or empty");
        }

        ObjectId objectId=new ObjectId(productId);
        Optional<Product> product=productRepository.findById(objectId);
        return ProductMapper.toResponseDTO(product.orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId)));
    }


}
