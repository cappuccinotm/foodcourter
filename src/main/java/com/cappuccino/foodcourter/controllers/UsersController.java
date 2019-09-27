package com.cappuccino.foodcourter.controllers;

import com.cappuccino.foodcourter.models.db.User;
import com.cappuccino.foodcourter.resources.StaticStrings;
import com.cappuccino.foodcourter.services.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }


    @GetMapping("/api/user/getMe")
    public User getUserInformation(Principal principal){
        return usersService.getByEmail(principal.getName());
    }

    @GetMapping("/api/public/user/resetPassword")
    public ResponseEntity<String> resetPassword(
            @RequestParam(name = "email") String email
    ){
        try {
            usersService.resetUserPassword(email);
        }catch(UsernameNotFoundException e){
            return ResponseEntity
                    .badRequest()
                    .body(StaticStrings.NO_SUCH_EMAIL);
        }
        return ResponseEntity.ok(StaticStrings.PASSWORD_RESET_SUCCESS);
    }

}
