package com.cappuccino.foodcourter.services;

import com.cappuccino.foodcourter.models.api.UserRegistrationData;
import com.cappuccino.foodcourter.models.db.User;
import com.cappuccino.foodcourter.models.exceptions.RoleCodeSpecificationDeniedException;
import com.cappuccino.foodcourter.models.exceptions.RoleNotFoundException;
import com.cappuccino.foodcourter.models.exceptions.UsernameAlreadyTakenException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UsersService {

    User getByEmail(String email);
    void resetUserPassword(String email) throws UsernameNotFoundException;
    User register(UserRegistrationData user, User authorizedUser)
            throws UsernameAlreadyTakenException,
            RoleNotFoundException,
            RoleCodeSpecificationDeniedException;
}
