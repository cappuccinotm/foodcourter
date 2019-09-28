package com.cappuccino.foodcourter.controllers;

import com.cappuccino.foodcourter.models.api.ProductCreationData;
import com.cappuccino.foodcourter.models.db.Privilege;
import com.cappuccino.foodcourter.models.db.User;
import com.cappuccino.foodcourter.resources.StaticStrings;
import com.cappuccino.foodcourter.services.ProductsService;
import com.cappuccino.foodcourter.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Oybek Kasimov <MrKasimov> oibekkasymov@gmail.com
 * @project foodcourter
 * @since 28.09.2019
 */
@RestController
public class ProductController {

    private final ProductsService productsService;

    private final UsersService usersService;

    public ProductController(
            UsersService usersService,
            ProductsService productsService
    ) {
        this.usersService = usersService;
        this.productsService = productsService;
    }

    @PostMapping("/api/product/create")
    public ResponseEntity<String> createProduct(
            Principal principal,
            @RequestBody ProductCreationData productData
    ) {
        User user = usersService.getByEmail(principal.getName());
        if(
                user.hasPrivilege(Privilege.StandartPrivileges.CREATE_PRODUCTS)
                && productData.isValid()
        ){
            productsService.create(productData);
            return new ResponseEntity<>("", HttpStatus.OK);
        }

        return ResponseEntity
                .badRequest()
                .body(StaticStrings.INCORRECT_FORMAT);
    }
}
