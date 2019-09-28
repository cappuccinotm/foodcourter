package com.cappuccino.foodcourter.models.db;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Oybek Kasimov <MrKasimov> oibekkasymov@gmail.com
 * @project foodcourter
 * @since 28.09.2019
 */
@Entity
@Table(name = "shopping_centers")
public class ShoppingCenter extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "shoppingCenter")
    private Set<Branch> branches;

    public Set<Branch> getBranches() {
        return branches;
    }

    public ShoppingCenter setBranches(Set<Branch> branches) {
        this.branches = branches;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public ShoppingCenter setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShoppingCenter setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ShoppingCenter setAddress(String address) {
        this.address = address;
        return this;
    }
}
