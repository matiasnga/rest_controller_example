package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.dto.ProductDto;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product newProduct);

    Product findProductById(Long id);

    List<Product> findProductByName(String name);

    void deleteProductById(Long id);

    Product updateProduct(Long id, Product updateProduct);

    List<Product> listAllOrderByPrice();
}
