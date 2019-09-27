package com.cappuccino.foodcourter.models.db;

import javax.persistence.*;

@Entity
@Table(name = "privileges")
public class Privilege {

    public final static class StandartPrivileges {
        public static final String CREATE_NEW_BACKEND_USERS = "create_new_backend_users";
        public static final String EDIT_BACKEND_USERS = "edit_backend_users";
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
