package com.vanitha.bookstore.catalog.domain;

public class ProductNotFoundException extends  Exception{
    public ProductNotFoundException(String message){
     super(message);
    }
    public static ProductNotFoundException forCode(String code){
        return new ProductNotFoundException("Product Not Found with code "+ code);
    }
}
