package com.abnamro.retailer.service;

import com.abnamro.retailer.entity.Product;
import com.abnamro.retailer.exception.InvalidInputException;
import com.abnamro.retailer.repository.ProductRepository;
import com.abnamro.retailer.util.ErrorConstants;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        Product savedProduct = productRepository.findByName(product.getName());
        if (savedProduct != null) {
            throw new InvalidInputException(ErrorConstants.ERROR_NAME_ALREADY_USED);
        }

        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> findAllById(Set<Long> ids) {
        return productRepository.findAllById(ids);
    }
}
