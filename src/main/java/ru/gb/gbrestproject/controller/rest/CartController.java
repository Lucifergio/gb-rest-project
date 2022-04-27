package ru.gb.gbrestproject.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.gbrestproject.controller.dto.CartDto;
import ru.gb.gbrestproject.controller.dto.ManufacturerDto;
import ru.gb.gbrestproject.controller.dto.ProductDto;
import ru.gb.gbrestproject.service.CartGateway;
//import ru.gb.gbrestproject.service.ManufacturerGateway;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartGateway cartGateway;

    @PostMapping("/create")
    public CartDto createCart (@RequestBody CartDto cartDto)  {
        return cartGateway.create(cartDto);
    }

    @DeleteMapping("/deleteCart")
    public void deleteCart () {
        cartGateway.deleteCart();
    }

    @GetMapping
    public List<ProductDto> getProductListInCart() {
        return cartGateway.getProductListInCart();
    }

    @PostMapping("/add")
    public ResponseEntity<ProductDto> addProductInCart (@RequestBody ProductDto productDto) {
       return cartGateway.addProductInCart(productDto);
    }
    @DeleteMapping("/delete/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductFromCart (@PathVariable("productId") Long id) {
        cartGateway.deleteProductFromCart(id);
    }
}
