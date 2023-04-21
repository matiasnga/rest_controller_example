package com.example.demo.product;

import com.example.demo.DemoApplicationTests;
import com.example.demo.domain.Product;
import com.example.demo.dto.ProductDto;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest extends DemoApplicationTests {
    @Test
    @SneakyThrows
    public void saveProduct_withFieldsValid_ReturnStatus201Created() {
        ProductDto productdto = new ProductDto();
        productdto.setNombre("Speaker");
        productdto.setDescripcion("JBL 300");
        productdto.setPrecio(99.3);
        productdto.setCantidad(1);
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(productdto);

        mockMvc.perform(post("/product")
                        .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath(".id").value(4))
                .andExpect(jsonPath(".nombre").value("Speaker"))
                .andExpect(jsonPath(".descripcion").value("JBL 300"))
                .andExpect(jsonPath(".precio").value(99.3))
                .andExpect(jsonPath(".cantidad").value(1));
    }

    @Test
    @SneakyThrows
    public void updateProduct_withFieldsValid_ReturnStatus201Created() {
        long id = 1L;
        ProductDto productDto = new ProductDto();
        productDto.setNombre("CD");
        productDto.setDescripcion("Rubber Soul - The Beatles");
        productDto.setPrecio(25);
        productDto.setCantidad(5);
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(productDto);

        mockMvc.perform(put("/product/" + id)
                        .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath(".id").value(1))
                .andExpect(jsonPath(".nombre").value("CD"))
                .andExpect(jsonPath(".descripcion").value("Rubber Soul - The Beatles"))
                .andExpect(jsonPath(".precio").value(25.0))
                .andExpect(jsonPath(".cantidad").value(5));
    }

    @Test
    @SneakyThrows
    public void updateProduct_notFound_ReturnStatus404NotFound() {
        long id = 10L;
        Product product = new Product();
        product.setName("CD");
        product.setDescription("Rubber Soul - The Beatles");
        product.setPrice(15);
        product.setQuantity(5);
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(product);

        mockMvc.perform(put("/product/" + id)
                        .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                        .content(jsonRequest))
                .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    public void findProductById_withFieldsValid_ReturnStatus200ok() {
        mockMvc.perform(get("/product/findById/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".id").value(2))
                .andExpect(jsonPath(".nombre").value("CD"))
                .andExpect(jsonPath(".descripcion").value("Rubber Soul - The Beatles"))
                .andExpect(jsonPath(".precio").value(19.99))
                .andExpect(jsonPath(".cantidad").value(2));
    }

    @Test
    @SneakyThrows
    public void findProductById_notFound_ReturnStatus404notFound() {
        mockMvc.perform(get("/product/findById/10"))
                .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    public void findProductByName_withFieldsValid_ReturnStatus200ok() {
        String name = "CD";
        mockMvc.perform(get("/product/findByName?name=" + name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(2))
                .andExpect(jsonPath("$[0].nombre").value("CD"))
                .andExpect(jsonPath("$[0].descripcion").value("Rubber Soul - The Beatles"))
                .andExpect(jsonPath("$[0].precio").value(19.99))
                .andExpect(jsonPath("$[0].cantidad").value(2));
    }

    @Test
    @SneakyThrows
    public void deleteById_withFieldsValid_ReturnStatus200ok() {
        long id = 3;
        mockMvc.perform(delete("/product/delete/" + id))
                .andExpect(status().isOk());
        mockMvc.perform(get("/product/findById/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    public void listAllProductsOrderByPrice_ReturnStatus200ok() {
        mockMvc.perform(get("/product/listAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(3))
                .andExpect(jsonPath("$[0].nombre").value("Pencil"))
                .andExpect(jsonPath("$[0].descripcion").value("Blue Ink Pencil"))
                .andExpect(jsonPath("$[0].precio").value(0.95))
                .andExpect(jsonPath("$[0].cantidad").value(20))

                .andExpect(jsonPath("$[1].id").value(1))
                .andExpect(jsonPath("$[1].nombre").value("Book"))
                .andExpect(jsonPath("$[1].descripcion").value("It - Stephen King"))
                .andExpect(jsonPath("$[1].precio").value(10.05))
                .andExpect(jsonPath("$[1].cantidad").value(1))

                .andExpect(jsonPath("$[2].id").value(2))
                .andExpect(jsonPath("$[2].nombre").value("CD"))
                .andExpect(jsonPath("$[2].descripcion").value("Rubber Soul - The Beatles"))
                .andExpect(jsonPath("$[2].precio").value(19.99))
                .andExpect(jsonPath("$[2].cantidad").value(2));
    }
}
