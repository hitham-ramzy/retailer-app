package com.abnamro.retailer.resource;

import com.abnamro.retailer.entity.Product;
import com.abnamro.retailer.entity.criteria.ProductCriteria;
import com.abnamro.retailer.entity.dto.ProductDTO;
import com.abnamro.retailer.mapper.ProductMapper;
import com.abnamro.retailer.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductResource {

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(ProductCriteria productCriteria) {
        List<Product> products = productService.findAll(productCriteria);
        return ResponseEntity.ok(products);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getOneById(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody @Valid ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.mapDtoToProduct(productDTO);
        Product savedProduct = productService.save(product);
        return ResponseEntity.ok(savedProduct);
    }

}
