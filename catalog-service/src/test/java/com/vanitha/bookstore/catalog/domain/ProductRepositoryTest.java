package com.vanitha.bookstore.catalog.domain;

import com.vanitha.bookstore.catalog.ContainerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(
        properties = {
                "spring.test.database.replace =none",
                "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///postgres"
        }
)
//@Import(ContainerConfig.class)
@Sql("/test-data.sql")
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void shouldGetAllProducts(){
        List<ProductEntity> products=productRepository.findAll();
        assertThat(products).hasSize(15);
    }
    @Test
    public void shouldGetAllProductsByCode(){
        ProductEntity productEntity = productRepository.findByCode("P100").orElseThrow();
        assertThat(productEntity.getCode()).isEqualTo("P100");
        assertThat(productEntity.getImageUrl()).isEqualTo("https://images.gr-assets.com/books/1447303603l/2767052.jpg");
        assertThat(productEntity.getDescription()).isEqualTo("Winning will make you famous. Losing means certain death...");
        assertThat(productEntity.getName()).isEqualTo("The Hunger Games");
        assertThat(productEntity.getPrice()).isEqualTo(new BigDecimal("34.0"));
    }
    @Test
    void shouldReturnEmptyWhenProductCodeDoesNotExist(){
        assertThat(productRepository.findByCode("000")).isEmpty();
    }
}