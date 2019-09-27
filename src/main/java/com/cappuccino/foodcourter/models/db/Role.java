package com.cappuccino.foodcourter.models.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Yelshat Duskaliyev <semior> ura2178@gmail.com
 * @project foodcourter
 * @since 27.09.2019
 */
@Entity
@Table(name = "roles")
public class Role extends Auditable {

    public final static class StandardRoles {
        public static final String SUPERUSER = "SUPERUSER";
        public static final String CUSTOMER = "CUSTOMER";
        public static final String OPERATOR = "OPERATOR";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "role")
    private Set<User> users;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.DETACH
    )
    @JoinTable(
            joinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id",
                    referencedColumnName = "id"
            )
    )
    private Set<Privilege> privileges;

    public String getCode() {
        return code;
    }

    public Role setCode(String code) {
        this.code = code;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Role setId(Integer id) {
        this.id = id;
        return this;
    }

    @JsonIgnore
    public Set<User> getUsers() {
        return users;
    }

    public Role setUsers(Set<User> users) {
        this.users = users;
        return this;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public Role setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
        return this;
    }

    public JSONObject toJSONObject(){
        return new JSONObject()
                .put("id", id)
                .put("code", code)
                .put("privileges", privileges);
    }

}
