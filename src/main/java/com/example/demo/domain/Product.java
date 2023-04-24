package com.example.demo.domain;
import lombok.*;
import javax.persistence.*;

@Data
@Entity
public class  Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private int quantity;
}