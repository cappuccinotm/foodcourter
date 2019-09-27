package com.cappuccino.foodcourter.models.db;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "vendors")
public class Vendor extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "vendor")
    private Set<Branch> branches;

    public Integer getId() {
        return id;
    }

    public Vendor setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Vendor setName(String name) {
        this.name = name;
        return this;
    }

    public Set<Branch> getBranches() {
        return branches;
    }

    public Vendor setBranches(Set<Branch> branches) {
        this.branches = branches;
        return this;
    }
}
