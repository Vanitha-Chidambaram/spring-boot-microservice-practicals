package com.vanitha.bookstore.catalog.web.controllers;

import com.vanitha.bookstore.catalog.domain.PagedResult;
import com.vanitha.bookstore.catalog.domain.Product;
import com.vanitha.bookstore.catalog.domain.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
class ProductController {

    private final ProductService  productService;
    ProductController(ProductService productService){
        this.productService = productService;
    }
    @GetMapping
    PagedResult<Product> getProducts(@RequestParam(name = "page", defaultValue = "1")int pageNo){
        return productService.getProducts(pageNo);
    }
}
