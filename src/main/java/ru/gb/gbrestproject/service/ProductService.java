package ru.gb.gbrestproject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.gbrestproject.controller.dto.ProductDto;
import ru.gb.gbrestproject.dao.CartDao;
import ru.gb.gbrestproject.dao.ProductDao;
import ru.gb.gbrestproject.entity.Cart;
import ru.gb.gbrestproject.entity.Product;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductDao productDao;
    private final CartDao cartDao;

    public ProductDto save(ProductDto productDto) {

        Product savingProduct;

        if (productDto.getId() != null) {
            Optional<Product> productFromDBOptional = productDao.findById(productDto.getId());
            savingProduct = productFromDBOptional.orElseGet(Product::new);
        } else {
            savingProduct = new Product();
        }

        savingProduct.setTitle(productDto.getTitle());
        savingProduct.setCost(productDto.getCost());
        savingProduct.setManufactureDate(productDto.getManufactureDate());
        savingProduct.setStatus(productDto.getStatus());
        savingProduct = productDao.save(savingProduct);
        productDto.setId(savingProduct.getId());
        return productDto;
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productDao.findById(id).orElse(null);
    }

    @Transactional
    public List<Product> findAll() {
        return productDao.findAll();
    }


    public void deleteById(Long id) {
        try {
            productDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
        }
    }

    @Transactional
    public List<Product> allProductInCart() {
        return cartDao.getById(1L).getProducts();
    }

    public Product addProductInCart(Long id) {
        Cart cart = cartDao.getById(1L);

        Product product = productDao.getById(id);
        cart.addProduct(product);
        cartDao.save(cart);
        return product;
    }

    public void deleteFromCartById(Long id) {
        Cart cart = cartDao.getById(1L);
        for (Product prod : cart.getProducts()) {
            if (prod.getId().equals(id)) {
                cart.getProducts().remove(prod);
                return;
            }
        }
    }

}
