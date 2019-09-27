package com.cappuccino.foodcourter.models.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Yelshat Duskaliyev <semior> ura2178@gmail.com
 * @project foodcourter
 * @since 27.09.2019
 */
public class UserRegistrationData {
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

    public UserRegistrationData setRoleCode(String roleCode) {
        this.roleCode = roleCode;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserRegistrationData setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationData setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserRegistrationData setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
