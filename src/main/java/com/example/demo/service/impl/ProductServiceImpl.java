package com.example.demo.service.impl;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product saveProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }

    @Override
    public Product updateProduct(Long id, Product updateProduct) {
        this.findProductById(id);
        updateProduct.setId(id);
        return productRepository.save(updateProduct);
    }

    @Override
    public List<Product> listAllOrderByPrice() {
        return productRepository.findAll()
                .stream()
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .collect(Collectors.toList());
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Product> findProductByName(String name) {
        List<Product> products = productRepository.findByName(name);
        return products.stream()
                .filter(e -> e.getName().contains(name))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProductById(Long id) {
        this.findProductById(id);
        productRepository.deleteById(id);
    }

}
