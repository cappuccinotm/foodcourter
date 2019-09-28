package com.cappuccino.foodcourter.repositories;

import com.cappuccino.foodcourter.models.db.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Oybek Kasimov <MrKasimov> oibekkasymov@gmail.com
 * @project foodcourter
 * @since 28.09.2019
 */
@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer> {
}
