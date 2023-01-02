package com.abnamro.retailer.resource;

import com.abnamro.retailer.entity.Product;
import com.abnamro.retailer.entity.criteria.ProductCriteria;
import com.abnamro.retailer.entity.dto.ProductDTO;
import com.abnamro.retailer.mapper.ProductMapper;
import com.abnamro.retailer.service.ProductService;
import static com.abnamro.retailer.util.SecurityUtils.ROLE_ADMIN;
import static com.abnamro.retailer.util.SecurityUtils.ROLE_USER;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * The type Product resource.
 */
@RestController
@RequestMapping("/api/products")
@Secured({ROLE_ADMIN})
public class ProductResource {

    private final ProductService productService;

    /**
     * Instantiates a new Product resource.
     *
     * @param productService the product service
     */
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Gets all.
     *
     * @param productCriteria the product criteria
     * @return the all
     */
    @GetMapping
    @Secured({ROLE_ADMIN, ROLE_USER})
    public ResponseEntity<List<Product>> getAll(ProductCriteria productCriteria) {
        List<Product> products = productService.findAll(productCriteria);
        return ResponseEntity.ok(products);
    }

    /**
     * Gets one by id.
     *
     * @param id the id
     * @return the one by id
     */
    @GetMapping("/{id}")
    @Secured({ROLE_ADMIN, ROLE_USER})
    public ResponseEntity<Product> getOneById(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    /**
     * Create response entity.
     *
     * @param productDTO the product dto
     * @return the response entity
     */
    @PostMapping
    @Secured({ROLE_ADMIN})
    public ResponseEntity<Product> create(@RequestBody @Valid ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.mapDtoToProduct(productDTO);
        Product savedProduct = productService.save(product);
        return ResponseEntity.ok(savedProduct);
    }

}
