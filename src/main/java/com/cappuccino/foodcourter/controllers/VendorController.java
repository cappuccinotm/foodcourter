package com.cappuccino.foodcourter.controllers;

import com.cappuccino.foodcourter.models.api.VendorDTO;
import com.cappuccino.foodcourter.models.db.*;
import com.cappuccino.foodcourter.resources.StaticStrings;
import com.cappuccino.foodcourter.services.FileAttachmentsService;
import com.cappuccino.foodcourter.services.UsersService;
import com.cappuccino.foodcourter.services.VendorService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
            @RequestParam(name = "name") String name,
            @RequestPart(value = "image", required = false) final MultipartFile[] images
    ){
        User user = usersService.getByEmail(principal.getName());

        if (!user.hasPrivilege(Privilege.StandartPrivileges.CREATE_VENDORS) || name.isEmpty() || images.length == 0) {
            return ResponseEntity
                    .badRequest()
                    .body(StaticStrings.INCORRECT_FORMAT);
        }

        try {
            FileAttachment fileAttachment = fileAttachmentsService.saveAttachment(images[0].getInputStream());
            vendorService.create(new VendorDTO(name, fileAttachment));
        } catch (IOException e) {
            return ResponseEntity
                    .badRequest()
                    .body(StaticStrings.INCORRECT_FORMAT);
        }
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
