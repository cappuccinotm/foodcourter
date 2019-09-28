package com.cappuccino.foodcourter.repositories;

import com.cappuccino.foodcourter.models.db.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Yelshat Duskaliyev <semior> ura2178@gmail.com
 * @project foodcourter
 * @since 28.09.2019
 */
public interface VendorsRepository extends JpaRepository<Vendor, Integer> {
}
