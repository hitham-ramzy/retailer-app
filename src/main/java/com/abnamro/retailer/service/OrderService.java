package com.abnamro.retailer.service;

import com.abnamro.retailer.entity.Order;
import com.abnamro.retailer.entity.OrderProduct;
import com.abnamro.retailer.entity.Product;
import com.abnamro.retailer.entity.dto.OrderDTO;
import com.abnamro.retailer.entity.dto.OrderProductDTO;
import com.abnamro.retailer.exception.InvalidInputException;
import com.abnamro.retailer.mapper.OrderMapper;
import com.abnamro.retailer.repository.OrderRepository;
import com.abnamro.retailer.util.ErrorConstants;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order save(@Valid OrderDTO orderDTO) {
        Order order = mapOrderDtoToOrder(orderDTO);
        return orderRepository.save(order);
    }

    private Order mapOrderDtoToOrder(OrderDTO orderDTO) {
        Set<Long> productIds = orderDTO.getProducts().stream()
                .map(OrderProductDTO::getProductId)
                .collect(Collectors.toSet());

        Map<Long, Integer> productQuantityMap = orderDTO.getProducts().stream()
                .collect(Collectors.toMap(OrderProductDTO::getProductId, OrderProductDTO::getQuantity));

        List<Product> products = productService.findAllById(productIds);
        if (products.size() != productIds.size()) {
            throw new InvalidInputException(ErrorConstants.ERROR_PRODUCT_IDS_NOT_CORRECT);
        }

        Order order = OrderMapper.INSTANCE.mapDtoToOrder(orderDTO);
        List<OrderProduct> orderProducts = products.stream().map(product -> OrderProduct.builder()
                        .order(order)
                        .product(product)
                        .quantity(productQuantityMap.get(product.getId()))
                        .build())
                .collect(Collectors.toList());
        order.setOrderProducts(orderProducts);
        return order;
    }
}
