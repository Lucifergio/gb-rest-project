package ru.gb.gbrestproject.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.gbrestproject.controller.dto.CartDto;
import ru.gb.gbrestproject.controller.dto.ProductDto;

import java.util.List;

@FeignClient(url = "localhost:33873/cart", value = "cartGateway")
public interface CartGateway {

    @PostMapping("/create")
    CartDto create(@RequestBody CartDto cartDto);

    @GetMapping
    List<ProductDto> getProductListInCart();

    @PostMapping("/add")
    ResponseEntity<ProductDto> addProductInCart (@RequestBody ProductDto productDto);

    @DeleteMapping("/delete/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteProductFromCart (@PathVariable("productId") Long id);

    @DeleteMapping("/deleteCart")
    void deleteCart();
}
