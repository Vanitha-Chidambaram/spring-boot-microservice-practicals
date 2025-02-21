package com.vanitha.bookstore.catalog.domain;

import com.vanitha.bookstore.catalog.ApplicationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

   private final ProductRepository productRepository;
   private final ApplicationProperties applicationProperties;

    ProductService(ProductRepository productRepository,ApplicationProperties properties){
        this.productRepository = productRepository;
        this.applicationProperties = properties;
    }
    public PagedResult<Product> getProducts(int pageNo){
        Sort sort = Sort.by("name").ascending();
        Pageable pageable = PageRequest.of(pageNo, applicationProperties.pageSize(), sort);
        var productPage =  productRepository.findAll(pageable).map(ProductMapper::toProduct);
        PagedResult<Product> pagedResult = new PagedResult<>(
                productPage.getContent(),
                productPage.getTotalElements(),
                productPage.getNumber()+1,
                productPage.getTotalPages(),
                productPage.isFirst(),
                productPage.isLast(),
                productPage.hasNext(),
                productPage.hasPrevious()
        );
        return pagedResult;
    }
}
