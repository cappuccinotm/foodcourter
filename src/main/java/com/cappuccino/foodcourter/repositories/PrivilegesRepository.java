package com.cappuccino.foodcourter.repositories;

import com.cappuccino.foodcourter.models.db.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegesRepository extends JpaRepository<Privilege, Integer> {

    Privilege findByCode(String code);

}
