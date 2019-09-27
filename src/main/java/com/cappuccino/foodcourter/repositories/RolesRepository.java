package com.cappuccino.foodcourter.repositories;

import com.cappuccino.foodcourter.models.db.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Yelshat Duskaliyev <semior> ura2178@gmail.com
 * @project foodcourter
 * @since 27.09.2019
 */
public interface RolesRepository extends JpaRepository<Role, Integer> {

    Role findByCode(String code);

}
