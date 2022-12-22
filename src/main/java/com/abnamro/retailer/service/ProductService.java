package com.abnamro.retailer.service;

import com.abnamro.retailer.entity.Product;
import com.abnamro.retailer.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        // TODO :: validate
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
