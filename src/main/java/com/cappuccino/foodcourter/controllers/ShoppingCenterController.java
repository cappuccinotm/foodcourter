package com.cappuccino.foodcourter.controllers;

import com.cappuccino.foodcourter.models.db.Branch;
import com.cappuccino.foodcourter.models.db.Privilege;
import com.cappuccino.foodcourter.models.db.ShoppingCenter;
import com.cappuccino.foodcourter.models.db.User;
import com.cappuccino.foodcourter.resources.StaticStrings;
import com.cappuccino.foodcourter.services.ShoppingCentersService;
import com.cappuccino.foodcourter.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

/**
 * @author Oybek Kasimov <MrKasimov> oibekkasymov@gmail.com
 * @project foodcourter
 * @since 28.09.2019
 */
@RestController
@RequestMapping("/api/shoppingCenter")
public class ShoppingCenterController {
    private final UsersService usersService;
    private final ShoppingCentersService shoppingCentersService;

    public ShoppingCenterController(
            UsersService usersService,
            ShoppingCentersService shoppingCentersService) {
        this.usersService = usersService;
        this.shoppingCentersService = shoppingCentersService;
    }


    @GetMapping("/get")
    public ResponseEntity<?> getShoppingCenters(
            Principal principal
    ) {
        User user = usersService.getByEmail(principal.getName());
        if (!user.hasPrivilege(Privilege.StandartPrivileges.VIEW_SHOPPING_CENTERS)) {
            return ResponseEntity
                    .badRequest()
                    .body(StaticStrings.NO_PRIVILEGE);
        }
        List<ShoppingCenter> shoppingCenterList = shoppingCentersService.getAll();
        return ResponseEntity.ok(shoppingCenterList);
    }

    @GetMapping("/branches/get")
    public ResponseEntity<?> getBranches(
            Principal principal,
            @RequestParam(name = "id") Integer id
    ) {
        User user = usersService.getByEmail(principal.getName());
        if (!user.hasPrivilege(Privilege.StandartPrivileges.VIEW_SHOPPING_CENTERS)) {
            return ResponseEntity
                    .badRequest()
                    .body(StaticStrings.NO_PRIVILEGE);
        }
        if (!shoppingCentersService.isIdValid(id)) {
            return ResponseEntity
                    .badRequest()
                    .body(StaticStrings.INVALID_ID);
        }
        Set<Branch> branchSet = shoppingCentersService.getBranchesOfShoppingCenter(id);
        return ResponseEntity.ok(branchSet);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCenter(
            Principal principal,
            @RequestBody ShoppingCenter shoppingCenter
    ) {
        User user = usersService.getByEmail(principal.getName());
        if (!user.hasPrivilege(Privilege.StandartPrivileges.CREATE_SHOPPING_CENTERS)) {
            return ResponseEntity
                    .badRequest()
                    .body(StaticStrings.NO_PRIVILEGE);
        }
        shoppingCentersService.createCenter(shoppingCenter);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
