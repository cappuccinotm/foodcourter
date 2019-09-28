package com.cappuccino.foodcourter.services;

import com.cappuccino.foodcourter.models.db.Branch;
import com.cappuccino.foodcourter.models.db.ShoppingCenter;
import com.cappuccino.foodcourter.repositories.ShoppingCentersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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

    public Set<Branch> getBranchesOfShoppingCenter(Integer id) {
        return shoppingCentersRepository.findById(id).get().getBranches();
    }

    public Boolean isIdValid(Integer id) {
        return shoppingCentersRepository.findById(id).isPresent();
    }

    public void createCenter(ShoppingCenter shoppingCenter) {
        shoppingCentersRepository.save(shoppingCenter);
    }
}

