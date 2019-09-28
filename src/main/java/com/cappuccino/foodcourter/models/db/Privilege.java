package com.cappuccino.foodcourter.models.db;

import javax.persistence.*;

@Entity
@Table(name = "privileges")
public class Privilege {

    public final static class StandartPrivileges {
        public static final String CREATE_NEW_BACKEND_USERS = "create_new_backend_users";
        public static final String EDIT_BACKEND_USERS = "edit_backend_users";
        public static final String CREATE_VENDORS = "create_vendors";
        public static final String EDIT_VENDORS = "edit_vendors";
        public static final String CREATE_BRANCHES = "create_branches";
        public static final String EDIT_BRANCHES = "edit_branches";
        public static final String CREATE_ORDERS = "create_orders";
        public static final String EDIT_ORDERS = "edit_orders";
        public static final String VIEW_ORDERS = "view_orders";
        public static final String CREATE_PRODUCTS = "create_products";
        public static final String EDIT_PRODUCTS = "edit_products";
        public static final String CREATE_SHOPPING_CENTERS = "create_shopping_centers";
        public static final String VIEW_SHOPPING_CENTERS = "view_shopping_centers";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "code")
    private String code;

    public Integer getId() {
        return id;
    }

    public Privilege setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Privilege setCode(String code) {
        this.code = code;
        return this;
    }
}
