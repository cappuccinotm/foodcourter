package com.cappuccino.foodcourter.controllers;

import com.cappuccino.foodcourter.models.api.VendorDTO;
import com.cappuccino.foodcourter.models.db.Privilege;
import com.cappuccino.foodcourter.models.db.Product;
import com.cappuccino.foodcourter.models.db.User;
import com.cappuccino.foodcourter.models.db.Vendor;
import com.cappuccino.foodcourter.resources.StaticStrings;
import com.cappuccino.foodcourter.services.FileAttachmentsService;
import com.cappuccino.foodcourter.services.UsersService;
import com.cappuccino.foodcourter.services.VendorService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

/**
 * @author Yelshat Duskaliyev <semior> ura2178@gmail.com
 * @project foodcourter
 * @since 28.09.2019
 */
@RestController
@RequestMapping("/api/vendor")
public class VendorController {

    private final VendorService vendorService;
    private final UsersService usersService;
    private final FileAttachmentsService fileAttachmentsService;
    private final Logger log = Logger.getLogger(VendorController.class);

    public VendorController(
            VendorService vendorService,
            UsersService usersService,
            FileAttachmentsService fileAttachmentsService
    ) {
        this.vendorService = vendorService;
        this.usersService = usersService;
        this.fileAttachmentsService = fileAttachmentsService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createVendor(
            Principal principal,
            @RequestPart final VendorDTO vendorDTO,
            @RequestPart(value = "image", required = false) final MultipartFile image
    ){
        User user = usersService.getByEmail(principal.getName());

        if(!user.hasPrivilege(Privilege.StandartPrivileges.CREATE_VENDORS) || !vendorDTO.isValid()){
            return ResponseEntity
                    .badRequest()
                    .body(StaticStrings.INCORRECT_FORMAT);
        }

        Vendor vendor = vendorService.create(vendorDTO);
        try {
            fileAttachmentsService.save(image, vendor.getId(), Vendor.class);
        } catch (IOException e) {
            log.error(e);
        }
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
