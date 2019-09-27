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

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.DETACH
    )
    @JoinTable(
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id",
                    referencedColumnName = "id"
            )
    )
    private Set<Privilege> privileges;

    @Column(name = "need_to_change_password", nullable = false)
    private Boolean needToChangePassword = true;

    public Boolean isNeedToChangePassword() {
        return needToChangePassword;
    }

    public User setNeedToChangePassword(Boolean needToChangePassword) {
        this.needToChangePassword = needToChangePassword;
        return this;
    }

    public List<GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Privilege privilege: privileges){
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

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public User setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
        return this;
    }

    public JSONObject toJSONObject(){
        return new JSONObject()
                .put("id", id)
                .put("email", email)
                .put("privileges", privileges)
                .put("needToChangePassword", needToChangePassword);
    }
}
