package com.cappuccino.foodcourter.services;

import com.cappuccino.foodcourter.models.api.ProductDTO;
import com.cappuccino.foodcourter.models.db.Product;
import com.cappuccino.foodcourter.repositories.ProductsRepository;
import org.springframework.stereotype.Service;

/**
 * @author Oybek Kasimov <MrKasimov> oibekkasymov@gmail.com
 * @project foodcourter
 * @since 28.09.2019
 */
@Service
public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepository productsRepository;

    public ProductsServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Product create(ProductDTO product) {
        return productsRepository.save(
                new Product()
                    .setName(product.getName())
                    .setDescription(product.getDescription())
                    .setNeedToPromote(product.getNeedToPromote())
                    .setPrice(product.getPrice())
        );
    }
}
