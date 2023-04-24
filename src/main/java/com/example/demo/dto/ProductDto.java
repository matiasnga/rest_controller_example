package com.example.demo.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidad;
}