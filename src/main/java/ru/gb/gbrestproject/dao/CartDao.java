package ru.gb.gbrestproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.gbrestproject.entity.Cart;

public interface CartDao extends JpaRepository<Cart, Long> {

    Cart save(Cart cart);
}
