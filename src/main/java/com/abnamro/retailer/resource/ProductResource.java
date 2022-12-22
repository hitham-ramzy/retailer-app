package com.abnamro.retailer.resource;

import com.abnamro.retailer.entity.Product;
import com.abnamro.retailer.entity.dto.ProductDTO;
import com.abnamro.retailer.mapper.ProductMapper;
import com.abnamro.retailer.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductResource {

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody ProductDTO productDTO){
        Product product = ProductMapper.INSTANCE.mapDtoToProduct(productDTO);
        Product savedProduct = productService.save(product);
        return ResponseEntity.ok(savedProduct);
    }


}
