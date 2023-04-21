package com.example.demo.controller;

import com.example.demo.domain.Product;
import com.example.demo.dto.ProductDto;
import com.example.demo.mappers.ProductMapper;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductMapper productMapper;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    ProductDto newProduct(@RequestBody ProductDto newProductDto) {
        Product product = productMapper.productDtoToProduct(newProductDto);
        return  productMapper.productToProductDto(productService.saveProduct(product));
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PutMapping(path = "/{id}")
    ProductDto updateProduct(@RequestBody ProductDto updateProductDto, @PathVariable Long id) {
        Product updateProduct = productMapper.productDtoToProduct(updateProductDto);
        return productMapper.productToProductDto(productService.updateProduct(id, updateProduct));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/findById/{id}")
    ProductDto getProductById(@PathVariable Long id) {
        return productMapper.productToProductDto(productService.findProductById(id));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/findByName")
    public List<ProductDto> findByName(@RequestParam String name) {
        return productMapper.productListToProductListDto(productService.findProductByName(name));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/listAll")
    public List<ProductDto> listAllOrderByPrice() {
        System.out.println(productService.listAllOrderByPrice());
        return productMapper.productListToProductListDto(productService.listAllOrderByPrice());
    }
}
