package com.example.demo.controller;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    Product newProduct(@RequestBody Product newProduct) {
        return productService.saveProduct(newProduct);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PutMapping("/{id}")
    Product updateProduct(@RequestBody Product updateProduct, @PathVariable Long id) {
        return productService.updateProduct(id, updateProduct);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/findById/{id}")
    Product getProductById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/findByName")
    public List<Product> findByName(@RequestParam String name) {
        return productService.findProductByName(name);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/listAll")
    public List<Product> listAllOrderByPrice() {
        System.out.println(productService.listAllOrderByPrice());
        return productService.listAllOrderByPrice();
    }
}
