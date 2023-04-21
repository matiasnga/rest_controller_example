package com.example.demo.employee;

import com.example.demo.DemoApplicationTests;
import com.example.demo.domain.Product;
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
        Product product = new Product();
        product.setName("Speaker");
        product.setDescription("JBL 300");
        product.setPrice(99.3);
        product.setQuantity(1);
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(product);

        mockMvc.perform(post("/product")
                        .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath(".id").value(4))
                .andExpect(jsonPath(".name").value("Speaker"))
                .andExpect(jsonPath(".description").value("JBL 300"))
                .andExpect(jsonPath(".price").value(99.3))
                .andExpect(jsonPath(".quantity").value(1));
    }

    @Test
    @SneakyThrows
    public void updateProduct_withFieldsValid_ReturnStatus201Created() {
        long id = 1L;
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
                .andExpect(status().isCreated())
                .andExpect(jsonPath(".id").value(1))
                .andExpect(jsonPath(".name").value("CD"))
                .andExpect(jsonPath(".description").value("Rubber Soul - The Beatles"))
                .andExpect(jsonPath(".price").value(15.0))
                .andExpect(jsonPath(".quantity").value(5));
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
                .andExpect(jsonPath(".name").value("CD"))
                .andExpect(jsonPath(".description").value("Rubber Soul - The Beatles"))
                .andExpect(jsonPath(".price").value(19.99))
                .andExpect(jsonPath(".quantity").value(2));
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
                .andExpect(jsonPath("$[0].name").value("CD"))
                .andExpect(jsonPath("$[0].description").value("Rubber Soul - The Beatles"))
                .andExpect(jsonPath("$[0].price").value(19.99))
                .andExpect(jsonPath("$[0].quantity").value(2));
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
                .andExpect(jsonPath("$[0].name").value("Pencil"))
                .andExpect(jsonPath("$[0].description").value("Blue Ink Pencil"))
                .andExpect(jsonPath("$[0].price").value(0.95))
                .andExpect(jsonPath("$[0].quantity").value(20))

                .andExpect(jsonPath("$[1].id").value(1))
                .andExpect(jsonPath("$[1].name").value("Book"))
                .andExpect(jsonPath("$[1].description").value("It - Stephen King"))
                .andExpect(jsonPath("$[1].price").value(10.05))
                .andExpect(jsonPath("$[1].quantity").value(1))

                .andExpect(jsonPath("$[2].id").value(2))
                .andExpect(jsonPath("$[2].name").value("CD"))
                .andExpect(jsonPath("$[2].description").value("Rubber Soul - The Beatles"))
                .andExpect(jsonPath("$[2].price").value(19.99))
                .andExpect(jsonPath("$[2].quantity").value(2));
    }
}
