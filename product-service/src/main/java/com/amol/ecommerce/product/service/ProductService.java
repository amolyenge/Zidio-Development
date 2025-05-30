package com.amol.ecommerce.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amol.ecommerce.product.dto.request.ProductRequest;
import com.amol.ecommerce.product.dto.response.ProductResponse;
import com.amol.ecommerce.product.model.Product;
import com.amol.ecommerce.product.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * Creates a new product and returns a response.
     *
     * @param productRequest the product request DTO
     * @return ProductResponse with the created product details
     */
    @Transactional
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = mapToEntity(productRequest);
        productRepository.save(product);
        log.info("Product created successfully with ID: {}", product.getId());
        return mapToResponse(product);
    }

    /**
     * Retrieves all products.
     *
     * @return List of ProductResponse
     */
    @Transactional(readOnly = true)
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id the product ID
     * @return ProductResponse
     */
    @Transactional(readOnly = true)
    public ProductResponse getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product with ID: {} not found", id);
                    return new RuntimeException("Product not found with ID: " + id);
                });
        return mapToResponse(product);
    }

    /**
     * Retrieves a product by its SKU code.
     *
     * @param skuCode the SKU code
     * @return ProductResponse
     */
    @Transactional(readOnly = true)
    public ProductResponse getProductBySkuCode(String skuCode) {
        Product product = productRepository.findBySkuCode(skuCode)
                .orElseThrow(() -> {
                    log.error("Product with SKU: {} not found", skuCode);
                    return new RuntimeException("Product not found for SKU: " + skuCode);
                });
        return mapToResponse(product);
    }

    /**
     * Checks if a product exists by its SKU code.
     *
     * @param skuCode the SKU code
     * @return boolean indicating whether the product exists
     */
    @Transactional(readOnly = true)
    public boolean isProductExists(String skuCode) {
        return productRepository.findBySkuCode(skuCode).isPresent();
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the product ID
     */
    @Transactional
    public void deleteProductById(String id) {
        if (!productRepository.existsById(id)) {
            log.error("Product with ID: {} not found", id);
            throw new RuntimeException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
        log.info("Product with ID: {} deleted successfully", id);
    }

    private Product mapToEntity(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .skuCode(productRequest.skuCode())
                .price(productRequest.price())
                .build();
    }

    private ProductResponse mapToResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getSkuCode(),
                product.getPrice()
        );
    }
}
