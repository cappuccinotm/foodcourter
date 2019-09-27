package com.cappuccino.foodcourter.services;

import com.cappuccino.foodcourter.models.db.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UsersService {

    User getByEmail(String email);
    void resetUserPassword(String email) throws UsernameNotFoundException;

}
