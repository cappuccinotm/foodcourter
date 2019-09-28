package com.cappuccino.foodcourter.services;

import com.cappuccino.foodcourter.models.db.ShoppingCenter;
import com.cappuccino.foodcourter.repositories.ShoppingCentersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Oybek Kasimov <MrKasimov> oibekkasymov@gmail.com
 * @project foodcourter
 * @since 28.09.2019
 */
@Service
public class ShoppingCentersService {
    private final ShoppingCentersRepository shoppingCentersRepository;

    public ShoppingCentersService(ShoppingCentersRepository shoppingCentersRepository) {
        this.shoppingCentersRepository = shoppingCentersRepository;
    }

    public List<ShoppingCenter> getAll() {
        return shoppingCentersRepository.findAll();
    }
}
