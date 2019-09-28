package com.cappuccino.foodcourter.models.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.json.JSONObject;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("UnusedReturnValue")
@Entity
@Table(name = "users")
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.DETACH
    )
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "need_to_change_password", nullable = false)
    private Boolean needToChangePassword = true;

    @Column(name = "phone_number")
    private String phoneNumber;

    public Boolean getNeedToChangePassword() {
        return needToChangePassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Role getRole() {
        return role;
    }

    @JsonIgnore
    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    public User setNeedToChangePassword(Boolean needToChangePassword) {
        this.needToChangePassword = needToChangePassword;
        return this;
    }

    public boolean hasPrivilege(String privilegeCode){
        for(Privilege privilege: role.getPrivileges())
            if(privilege.getCode().equals(privilegeCode))
                return true;
        return false;
    }

    public List<GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Privilege privilege: role.getPrivileges()){
            authorities.add((GrantedAuthority) privilege::getCode);
        }
        return authorities;
    }

    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public JSONObject toJSONObject(){
        return new JSONObject()
                .put("id", id)
                .put("email", email)
                .put("role", role.toJSONObject())
                .put("needToChangePassword", needToChangePassword);
    }
}
