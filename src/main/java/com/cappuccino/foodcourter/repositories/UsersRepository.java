package com.cappuccino.foodcourter.repositories;

import com.cappuccino.foodcourter.models.db.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
    User findFirstByEmail(String email);
    boolean existsByEmail(String email);

}
