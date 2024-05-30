package org.ag.productcatalog.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ag.productcatalog.dtos.ProductDto;
import org.ag.productcatalog.models.Product;
import org.ag.productcatalog.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void Test_GetProduct_WithValidId_ReturnsProductSuccessfully() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setPrice(12.0);
        product.setDescription("IPhone 15");
        product.setName("IPhone 15");

        when(productService.getProduct(anyLong())).thenReturn(product);

        mockMvc.perform(get("/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(product)))
                .andExpect(jsonPath("$.name").value("IPhone 15"))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.price").value(12.0));
    }

    @Test
    public void Test_GetAllProducts_RunsSuccessfully() throws Exception {
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setName("Iphone15");
        productList.add(product);

        when(productService.getProducts()).thenReturn(productList);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(objectMapper.writeValueAsString(productList)));
        //.string("[]"));
        //object -> json -> string
    }

    @Test
    public void Test_CreateProduct_ProductCreatedSuccessfully() throws Exception {
        Product expectedProduct = new Product();
        expectedProduct.setName("MacBook");
        expectedProduct.setDescription("MacBook Pro");
        expectedProduct.setPrice(12.0);
        expectedProduct.setId(2L);

        ProductDto expectedProductDto = new ProductDto();
        expectedProductDto.setName("MacBook");
        expectedProductDto.setDescription("MacBook Pro");
        expectedProductDto.setPrice(12.0);
        expectedProductDto.setId(2L);

        when(productService.createProduct(any(Product.class)))
            .thenReturn(expectedProduct);

        mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expectedProductDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$.name").value("MacBook"));
    }
}
