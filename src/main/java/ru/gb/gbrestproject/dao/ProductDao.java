package ru.gb.gbrestproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.gbrestproject.entity.Product;
import ru.gb.gbrestproject.entity.enums.Status;

import java.util.List;
import java.util.Optional;

public interface ProductDao extends JpaRepository<Product, Long> {
    List<Product> findAllByStatus(Status status);

    Optional<Product> findByTitle(String title);

    List<Product> findAllByTitleContaining(String title);

}
