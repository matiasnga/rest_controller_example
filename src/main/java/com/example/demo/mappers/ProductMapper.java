package com.example.demo.mappers;

import com.example.demo.domain.Product;
import com.example.demo.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "descripcion", target = "description")
    @Mapping(source = "precio", target = "price")
    @Mapping(source = "cantidad", target = "quantity")
    Product productDtoToProduct(ProductDto productDto);

    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "description", target = "descripcion")
    @Mapping(source = "price", target = "precio")
    @Mapping(source = "quantity", target = "cantidad")
    ProductDto productToProductDto(Product product);

    List<ProductDto> productListToProductListDto(List<Product> productList);



}
