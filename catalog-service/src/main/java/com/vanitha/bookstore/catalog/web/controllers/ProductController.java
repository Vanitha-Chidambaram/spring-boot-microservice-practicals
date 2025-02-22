package com.vanitha.bookstore.catalog.web.controllers;

import com.vanitha.bookstore.catalog.domain.PagedResult;
import com.vanitha.bookstore.catalog.domain.Product;
import com.vanitha.bookstore.catalog.domain.ProductNotFoundException;
import com.vanitha.bookstore.catalog.domain.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PagedResult<Product> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        return productService.getProducts(pageNo);
    }
    @GetMapping("/{code}")
    ResponseEntity<Product> getProductByCode(@PathVariable String code) throws ProductNotFoundException {
        return productService.getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(()->ProductNotFoundException.forCode(code));
    }
}
