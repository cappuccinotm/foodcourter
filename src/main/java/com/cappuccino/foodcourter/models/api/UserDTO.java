package com.cappuccino.foodcourter.models.api;

import com.cappuccino.foodcourter.models.db.Role;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Yelshat Duskaliyev <semior> ura2178@gmail.com
 * @project foodcourter
 * @since 27.09.2019
 */
public class UserDTO{
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("roleCode")
    private String roleCode;

    public String getRoleCode() {
        return roleCode;
    }

    public UserDTO setRoleCode(String roleCode) {
        this.roleCode = roleCode;
        return this;
    }

    public boolean isNonCustomerRole(){
        return roleCode != null && !roleCode.equals(Role.StandardRoles.CUSTOMER);
    }

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
