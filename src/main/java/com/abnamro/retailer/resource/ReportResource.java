package com.abnamro.retailer.resource;

import com.abnamro.retailer.entity.Order;
import com.abnamro.retailer.entity.Product;
import com.abnamro.retailer.entity.SaleAmountPerDay;
import com.abnamro.retailer.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportResource {

    private final ReportService reportService;

    public ReportResource(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("orders/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable Long id) {
        Order order = reportService.findOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("products/top-selling/today")
    public ResponseEntity<List<Product>> findTopSellingProductsOfToday() {
        List<Product> products = reportService.findTopSellingProductsOfToday();
        return ResponseEntity.ok(products);
    }

    @GetMapping("products/least-selling/month")
    public ResponseEntity<List<Product>> findLeastSellingProductsOfMonth() {
        List<Product> products = reportService.findLeastSellingProductsOfMonth();
        return ResponseEntity.ok(products);
    }

    @GetMapping("orders/count-sale")
    public ResponseEntity<List<SaleAmountPerDay>> calculateSaleAmountWithinTime(@RequestParam Date fromDate, @RequestParam Date toDate) {
        List<SaleAmountPerDay> totalAmount = reportService.calculateSaleAmountWithinTime(fromDate, toDate);
        return ResponseEntity.ok(totalAmount);
    }


}
