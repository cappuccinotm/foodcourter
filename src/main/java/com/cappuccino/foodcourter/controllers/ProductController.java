package com.cappuccino.foodcourter.controllers;

import com.cappuccino.foodcourter.models.api.ProductCreationData;
import com.cappuccino.foodcourter.models.db.Privilege;
import com.cappuccino.foodcourter.models.db.Product;
import com.cappuccino.foodcourter.models.db.User;
import com.cappuccino.foodcourter.resources.StaticStrings;
import com.cappuccino.foodcourter.services.FileAttachmentsService;
import com.cappuccino.foodcourter.services.ProductsService;
import com.cappuccino.foodcourter.services.UsersService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

/**
 * @author Oybek Kasimov <MrKasimov> oibekkasymov@gmail.com
 * @project foodcourter
 * @since 28.09.2019
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductsService productsService;
    private final UsersService usersService;
    private final FileAttachmentsService fileAttachmentsService;
    private final Logger log = Logger.getLogger(ProductController.class);

    public ProductController(
            UsersService usersService,
            ProductsService productsService,
            FileAttachmentsService fileAttachmentsService) {
        this.usersService = usersService;
        this.productsService = productsService;
        this.fileAttachmentsService = fileAttachmentsService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(
            Principal principal,
            @RequestParam(value = "name", required = false) final String name,
            @RequestParam(value = "price", required = false) final Double price,
            @RequestParam(value = "description", required = false) final String description,
            @RequestParam(value = "needToPromote", required = false) final Boolean needToPromote,
            @RequestParam(value = "images", required = false) final MultipartFile[] images
    ) {
        ProductCreationData productData = new ProductCreationData()
                .setName(name)
                .setDescription(description)
                .setPrice(price)
                .setNeedToPromote(needToPromote);

        User user = usersService.getByEmail(principal.getName());

        if(!user.hasPrivilege(Privilege.StandartPrivileges.CREATE_PRODUCTS) || !productData.isValid()){
            return ResponseEntity
                    .badRequest()
                    .body(StaticStrings.INCORRECT_FORMAT);
        }

        Product product = productsService.create(productData);
        try {
            fileAttachmentsService.saveAll(images, product.getId(), Product.class);
        } catch (IOException e) {
            log.error(e);
        }
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
