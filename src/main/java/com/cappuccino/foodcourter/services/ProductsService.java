package com.cappuccino.foodcourter.services;

import com.cappuccino.foodcourter.models.api.ProductCreationData;
import com.cappuccino.foodcourter.models.db.Product;
import com.cappuccino.foodcourter.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Oybek Kasimov <MrKasimov> oibekkasymov@gmail.com
 * @project foodcourter
 * @since 28.09.2019
 */
@Service
public class ProductsService {
    @Autowired
    private ProductsRepository productsRepository;

    public void create(ProductCreationData product) {
        productsRepository.save(
                new Product()
                .setName(product.getName())
                .setDescription(product.getDescription())
                .setNeedToPromote(product.getNeedToPromote())
                .setPrice(product.getPrice())
        );
    }
}
