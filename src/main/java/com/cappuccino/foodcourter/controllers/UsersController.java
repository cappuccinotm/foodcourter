package com.cappuccino.foodcourter.controllers;

import com.cappuccino.foodcourter.models.api.UserDTO;
import com.cappuccino.foodcourter.models.db.User;
import com.cappuccino.foodcourter.models.exceptions.RoleCodeSpecificationDeniedException;
import com.cappuccino.foodcourter.models.exceptions.RoleNotFoundException;
import com.cappuccino.foodcourter.models.exceptions.UsernameAlreadyTakenException;
import com.cappuccino.foodcourter.resources.StaticStrings;
import com.cappuccino.foodcourter.services.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/api/public/user/register")
    public ResponseEntity<String> registerUser(
            Principal principal,
            @RequestBody UserDTO userData
    ){
        User user = principal != null ? usersService.getByEmail(principal.getName()) : null;
        try{
            usersService.register(userData, user);
        }catch (UsernameAlreadyTakenException e){
            return ResponseEntity
                    .badRequest()
                    .body(StaticStrings.USERNAME_ALREADY_TAKEN);
        } catch (RoleNotFoundException e) {
            return ResponseEntity
                    .badRequest()
                    .body(StaticStrings.ROLE_CODE_NOT_FOUND);
        } catch (RoleCodeSpecificationDeniedException e) {
            return ResponseEntity
                    .badRequest()
                    .body(StaticStrings.YOU_MAY_NOT_SPECIFY_ROLE_CODE);
        }
        return ResponseEntity.ok("");
    }

}
